package com.kyalo.fbimostwanted.service;

import com.kyalo.fbimostwanted.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FbiServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private FbiService fbiService;

    private FbiApiResponse mockResponse;
    private FbiItem mockItem;

    @BeforeEach
    void setUp() {
        // Initialize mock data
        mockResponse = new FbiApiResponse();
        mockItem = new FbiItem();
        mockItem.setUid("123");
        mockItem.setTitle("John Doe");
        mockItem.setNationality("American");
        mockItem.setSex("Male");
        mockItem.setRace("White");
        mockItem.setAgeMin(30);
        mockItem.setAgeMax(40);
        mockItem.setHairColor("Black");
        mockItem.setEyeColor("Brown");
        mockItem.setSubjects(List.of("Cyber Crime"));
        mockResponse.setItems(List.of(mockItem));
        mockResponse.setTotal(1);
    }

    @Test
    void testFetchWantedPersons_Success() {
        when(restTemplate.getForObject(anyString(), eq(FbiApiResponse.class)))
                .thenReturn(mockResponse);

        PaginatedResponse<WantedPerson> response = fbiService.fetchWantedPersons(1, "John", "White", "American", "Male", 30, 40, "Black", "Brown", "Cyber Crimes", null);
        List<WantedPerson> result = response.getData();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getTitle());
        assertEquals("American", result.get(0).getNationality());
        assertEquals("Cyber Crimes", result.get(0).getCategory());

        verify(restTemplate, times(1)).getForObject(anyString(), eq(FbiApiResponse.class));
    }

    @Test
    void testFetchWantedPersons_EmptyResponse() {
        when(restTemplate.getForObject(anyString(), eq(FbiApiResponse.class)))
                .thenReturn(new FbiApiResponse());

        PaginatedResponse<WantedPerson> response = fbiService.fetchWantedPersons(1, null, null, null, null, null, null, null, null, null, null);
        assertTrue(response.getData().isEmpty());

        verify(restTemplate, times(1)).getForObject(anyString(), eq(FbiApiResponse.class));
    }

    @Test
    void testFetchWantedPersons_FilterByRace() {
        when(restTemplate.getForObject(anyString(), eq(FbiApiResponse.class)))
                .thenReturn(mockResponse);

        PaginatedResponse<WantedPerson> response = fbiService.fetchWantedPersons(1, null, "White", null, null, null, null, null, null, null, null);
        List<WantedPerson> result = response.getData();

        assertEquals(1, result.size());
        assertEquals("White", result.get(0).getRace());
    }

    @Test
    void testFetchWantedPersons_NoMatchingRace() {
        when(restTemplate.getForObject(anyString(), eq(FbiApiResponse.class)))
                .thenReturn(mockResponse);

        PaginatedResponse<WantedPerson> response = fbiService.fetchWantedPersons(1, null, "Asian", null, null, null, null, null, null, null, null);
        assertTrue(response.getData().isEmpty());
    }

    @Test
    void testFetchWantedPersons_FilterByCategory() {
        when(restTemplate.getForObject(anyString(), eq(FbiApiResponse.class)))
                .thenReturn(mockResponse);

        PaginatedResponse<WantedPerson> response = fbiService.fetchWantedPersons(1, null, null, null, null, null, null, null, null, "Cyber Crimes", null);
        List<WantedPerson> result = response.getData();

        assertEquals(1, result.size());
        assertEquals("Cyber Crimes", result.get(0).getCategory());
    }

    @Test
    void testFetchWantedPersons_FilterByAgeRange() {
        when(restTemplate.getForObject(anyString(), eq(FbiApiResponse.class)))
                .thenReturn(mockResponse);

        PaginatedResponse<WantedPerson> response = fbiService.fetchWantedPersons(1, null, null, null, null, 30, 40, null, null, null, null);
        List<WantedPerson> result = response.getData();

        assertEquals(1, result.size());
        assertTrue(result.get(0).getAgeMin() >= 30);
        assertTrue(result.get(0).getAgeMax() <= 40);
    }

    @Test
    void testFetchWantedPersons_NoMatchingAgeRange() {
        when(restTemplate.getForObject(anyString(), eq(FbiApiResponse.class)))
                .thenReturn(mockResponse);

        PaginatedResponse<WantedPerson> response = fbiService.fetchWantedPersons(1, null, null, null, null, 50, 60, null, null, null, null);
        assertTrue(response.getData().isEmpty());
    }

    @Test
    void testFetchWantedPersons_FilterByName() {
        when(restTemplate.getForObject(anyString(), eq(FbiApiResponse.class)))
                .thenReturn(mockResponse);

        PaginatedResponse<WantedPerson> response = fbiService.fetchWantedPersons(1, "John", null, null, null, null, null, null, null, null, null);
        List<WantedPerson> result = response.getData();

        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getTitle());
    }

    @Test
    void testFetchWantedPersons_FilterByHairColor() {
        when(restTemplate.getForObject(anyString(), eq(FbiApiResponse.class)))
                .thenReturn(mockResponse);

        PaginatedResponse<WantedPerson> response = fbiService.fetchWantedPersons(1, null, null, null, null, null, null, "Black", null, null, null);
        List<WantedPerson> result = response.getData();

        assertEquals(1, result.size());
        assertEquals("Black", result.get(0).getHairColor());
    }

    @Test
    void testFetchWantedPersons_FilterByEyeColor() {
        when(restTemplate.getForObject(anyString(), eq(FbiApiResponse.class)))
                .thenReturn(mockResponse);

        PaginatedResponse<WantedPerson> response = fbiService.fetchWantedPersons(1, null, null, null, null, null, null, null, "Brown", null, null);
        List<WantedPerson> result = response.getData();

        assertEquals(1, result.size());
        assertEquals("Brown", result.get(0).getEyeColor());
    }
}
