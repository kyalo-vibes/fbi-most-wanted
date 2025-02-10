package com.kyalo.fbimostwanted.service;

import com.kyalo.fbimostwanted.model.FbiApiResponse;
import com.kyalo.fbimostwanted.model.FbiItem;
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
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String FBI_API_URL = "https://api.fbi.gov/wanted/v1/list";

    @Cacheable(value = "wantedList", key = "'page_' + #page", unless = "#result.isEmpty()")
    public List<WantedPerson> fetchWantedPersons(int page) {
        logger.info("Fetching fresh data from FBI API, page: {}", page);

        String url = UriComponentsBuilder.fromHttpUrl(FBI_API_URL)
                .queryParam("page", page)
                .toUriString();

        var response = restTemplate.getForObject(url, FbiApiResponse.class);
        if (response != null && response.getItems() != null) {
            return response.getItems().stream().map(this::mapToWantedPerson).collect(Collectors.toList());
        }
        return List.of();
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