package com.kyalo.fbimostwanted.service;

import com.kyalo.fbimostwanted.model.FbiApiResponse;
import com.kyalo.fbimostwanted.model.FbiItem;
import com.kyalo.fbimostwanted.model.PaginatedResponse;
import com.kyalo.fbimostwanted.model.WantedPerson;
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

    public FbiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable(value = "wantedList", key = "'page_' + #page + " +
            "(#name != null ? '_name_' + #name : '') + " +
            "(#race != null ? '_race_' + #race : '') + " +
            "(#nationality != null ? '_nationality_' + #nationality : '') + " +
            "(#sex != null ? '_sex_' + #sex : '')", unless = "#result.data.isEmpty()")
    public PaginatedResponse<WantedPerson> fetchWantedPersons(int page, String name, String race, String nationality, String sex) {
        logger.info("Fetching fresh data from FBI API, page: {}, filters: name={}, race={}, nationality={}, sex={}",
                page, name, race, nationality, sex);

        String url = UriComponentsBuilder.fromHttpUrl(FBI_API_URL)
                .queryParam("page", page)
                .toUriString();

        var response = restTemplate.getForObject(url, FbiApiResponse.class);

        if (response != null && response.getItems() != null) {
            List<WantedPerson> filteredResults = response.getItems().stream()
                    .map(this::mapToWantedPerson)
                    .filter(person ->
                            (name == null || (person.getTitle() != null && person.getTitle().toLowerCase().contains(name.toLowerCase()))) &&
                                    (race == null || (person.getRace() != null && person.getRace().equalsIgnoreCase(race))) &&
                                    (nationality == null || (person.getNationality() != null && person.getNationality().equalsIgnoreCase(nationality))) &&
                                    (sex == null || (person.getSex() != null && person.getSex().equalsIgnoreCase(sex)))
                    )
                    .collect(Collectors.toList());

            int totalPages = (int) Math.ceil((double) response.getTotal() / filteredResults.size());
            return new PaginatedResponse<WantedPerson>(page, totalPages, response.getTotal(), filteredResults);
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

        if (item.getImages() != null && !item.getImages().isEmpty()) {
            person.setImageUrl(item.getImages().get(0).getLarge());
        }
        return person;
    }
}