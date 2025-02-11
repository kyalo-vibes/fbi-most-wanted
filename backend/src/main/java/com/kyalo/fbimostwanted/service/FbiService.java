package com.kyalo.fbimostwanted.service;

import com.kyalo.fbimostwanted.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FbiService {
    private static final Logger logger = LoggerFactory.getLogger(FbiService.class);
    private final RestTemplate restTemplate;
    private static final String FBI_API_URL = "https://api.fbi.gov/wanted/v1/list";
    private static final int PAGE_SIZE = 20; // Set the page size to 20

    public FbiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable(value = "wantedList", key = "'page_' + #page + " +
            "(#name != null ? '_name_' + #name : '') + " +
            "(#race != null ? '_race_' + #race : '') + " +
            "(#nationality != null ? '_nationality_' + #nationality : '') + " +
            "(#sex != null ? '_sex_' + #sex : '') + " +
            "(#ageMin != null ? '_ageMin_' + #ageMin : '') + " +
            "(#ageMax != null ? '_ageMax_' + #ageMax : '') + " +
            "(#hairColor != null ? '_hairColor_' + #hairColor : '') + " +
            "(#eyeColor != null ? '_eyeColor_' + #eyeColor : '')",
            unless = "#result.data.isEmpty()")
    public PaginatedResponse<WantedPerson> fetchWantedPersons(
            int page, String name, String race, String nationality, String sex,
            Integer ageMin, Integer ageMax, String hairColor, String eyeColor) {

        logger.info("Fetching fresh data from FBI API, page: {}, filters: name={}, race={}, nationality={}, sex={}, ageMin={}, ageMax={}, hairColor={}, eyeColor={}",
                page, name, race, nationality, sex, ageMin, ageMax, hairColor, eyeColor);

        // Construct the API URL with page and pageSize parameters
        String url = UriComponentsBuilder.fromHttpUrl(FBI_API_URL)
                .queryParam("page", page)
                .queryParam("pageSize", PAGE_SIZE) // Ensure only 20 results per request
                .toUriString();

        var response = restTemplate.getForObject(url, FbiApiResponse.class);

        if (response != null && response.getItems() != null) {
            // Apply additional filtering on top of API response
            List<WantedPerson> filteredResults = response.getItems().stream()
                    .map(this::mapToWantedPerson)
                    .filter(person ->
                            (name == null || (person.getTitle() != null && person.getTitle().toLowerCase().contains(name.toLowerCase()))) &&
                                    (race == null || (person.getRace() != null && person.getRace().equalsIgnoreCase(race))) &&
                                    (nationality == null || (person.getNationality() != null && person.getNationality().equalsIgnoreCase(nationality))) &&
                                    (sex == null || (person.getSex() != null && person.getSex().equalsIgnoreCase(sex))) &&
                                    (ageMin == null || (person.getAgeMin() != null && person.getAgeMin() >= ageMin)) &&
                                    (ageMax == null || (person.getAgeMax() != null && person.getAgeMax() <= ageMax)) &&
                                    (hairColor == null || (person.getHairColor() != null && person.getHairColor().toLowerCase().contains(hairColor.toLowerCase()))) &&
                                    (eyeColor == null || (person.getEyeColor() != null && person.getEyeColor().toLowerCase().contains(eyeColor.toLowerCase())))
                    )
                    .collect(Collectors.toList());

            int totalPages = (int) Math.ceil((double) response.getTotal() / PAGE_SIZE);

            return new PaginatedResponse<>(page, totalPages, response.getTotal(), filteredResults);
        }

        return new PaginatedResponse<>(page, 0, 0, List.of());
    }

    private WantedPerson mapToWantedPerson(FbiItem item) {
        WantedPerson person = new WantedPerson();
        person.setUid(item.getUid());
        person.setTitle(item.getTitle());
        person.setDescription(item.getDescription());
        person.setSex(item.getSex());
        person.setRace(item.getRace());
        person.setNationality(item.getNationality());
        person.setAliases(item.getAliases());
        person.setFieldOffices(item.getFieldOffices());
        person.setRewardText(item.getRewardText());
        person.setStatus(item.getStatus());
        person.setUrl(item.getUrl());
        person.setAgeMin(item.getAgeMin());
        person.setAgeMax(item.getAgeMax());
        person.setHairColor(item.getHairColor());
        person.setEyeColor(item.getEyeColor());

        if (item.getImages() != null && !item.getImages().isEmpty()) {
            person.setImageUrl(item.getImages().get(0).getLarge());
        }

        if (item.getFiles() != null && !item.getFiles().isEmpty()) {
            List<FileInfo> fileDetails = item.getFiles().stream()
                    .map(file -> new FileInfo(file.getName(), file.getUrl())) // Extract name + URL
                    .collect(Collectors.toList());
            person.setExternalFiles(fileDetails);
        }
        return person;
    }
}
