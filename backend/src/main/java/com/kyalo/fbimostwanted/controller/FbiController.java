package com.kyalo.fbimostwanted.controller;

import com.kyalo.fbimostwanted.model.PaginatedResponse;
import com.kyalo.fbimostwanted.model.WantedPerson;
import com.kyalo.fbimostwanted.service.FbiService;
import jakarta.validation.constraints.Min;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1.0.0/wanted")
public class FbiController {
    private final FbiService fbiService;

    public FbiController(FbiService fbiService) {
        this.fbiService = fbiService;
    }

    @GetMapping
    public PaginatedResponse<WantedPerson> getWantedPersons(
            @RequestParam(defaultValue = "1") @Min(1) int page,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String race,
            @RequestParam(required = false) String nationality,
            @RequestParam(required = false) String sex,
            @RequestParam(required = false) Integer ageMin,
            @RequestParam(required = false) Integer ageMax,
            @RequestParam(required = false) String hairColor,
            @RequestParam(required = false) String eyeColor,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String placeOfInterest) {

        return fbiService.fetchWantedPersons(page, name, race, nationality, sex, ageMin, ageMax, hairColor, eyeColor, category, placeOfInterest);
    }
}

