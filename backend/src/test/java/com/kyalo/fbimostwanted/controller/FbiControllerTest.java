package com.kyalo.fbimostwanted.controller;

import com.kyalo.fbimostwanted.FbiMostWantedApplication;
import com.kyalo.fbimostwanted.config.RedisTestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {FbiMostWantedApplication.class, RedisTestConfig.class})
@AutoConfigureMockMvc
class FbiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @BeforeEach
    void setUp() {
        // Flush Redis cache before each test to ensure a fresh state
        redisTemplate.getConnectionFactory().getConnection().flushAll();
    }

    @Test
    void testGetWantedPersons_PaginationWorks() throws Exception {
        mockMvc.perform(get("/api/v1.0.0/wanted?page=1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currentPage").value(1))
                .andExpect(jsonPath("$.data").isArray());
    }

    @Test
    void testGetWantedPersons_FilterByNationality() throws Exception {
        mockMvc.perform(get("/api/v1.0.0/wanted?page=1&nationality=Russian")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray());
    }

    @Test
    void testGetWantedPersons_FilterByAgeRange() throws Exception {
        mockMvc.perform(get("/api/v1.0.0/wanted?page=1&ageMin=25&ageMax=40")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray());
    }

    @Test
    void testGetWantedPersons_FilterByHairColor() throws Exception {
        mockMvc.perform(get("/api/v1.0.0/wanted?page=1&hairColor=Black")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray());
    }

    @Test
    void testGetWantedPersons_FilterByEyeColor() throws Exception {
        mockMvc.perform(get("/api/v1.0.0/wanted?page=1&eyeColor=Brown")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray());
    }

    @Test
    void testGetWantedPersons_FilterBySex() throws Exception {
        mockMvc.perform(get("/api/v1.0.0/wanted?page=1&sex=Male")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray());
    }

    @Test
    void testPerformance_CachedDataIsFaster() throws Exception {
        long startTime = System.currentTimeMillis();
        mockMvc.perform(get("/api/v1.0.0/wanted?page=1"));
        long apiTime = System.currentTimeMillis() - startTime;

        // Second request should be from Redis
        startTime = System.currentTimeMillis();
        mockMvc.perform(get("/api/v1.0.0/wanted?page=1"));
        long cacheTime = System.currentTimeMillis() - startTime;

        // Ensure cached response is faster
        System.out.println("API Response Time: " + apiTime + "ms");
        System.out.println("Cached Response Time: " + cacheTime + "ms");

        assert cacheTime < apiTime;
    }
}
