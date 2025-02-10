package com.kyalo.fbimostwanted.controller;

import com.kyalo.fbimostwanted.model.WantedPerson;
import com.kyalo.fbimostwanted.service.FbiService;
import jakarta.validation.constraints.Min;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1.0.0/wanted")
public class FbiController {
    private final FbiService fbiService;

    public FbiController(FbiService fbiService) {
        this.fbiService = fbiService;
    }

    @GetMapping
    public List<WantedPerson> getWantedPersons(@RequestParam(defaultValue = "1") @Min(1) int page) {
        return fbiService.fetchWantedPersons(page);
    }
}
