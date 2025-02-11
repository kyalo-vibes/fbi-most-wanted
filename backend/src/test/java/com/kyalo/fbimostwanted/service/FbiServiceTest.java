package com.kyalo.fbimostwanted.service;

import com.kyalo.fbimostwanted.model.FbiApiResponse;
import com.kyalo.fbimostwanted.model.FbiItem;
import com.kyalo.fbimostwanted.model.WantedPerson;
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
        mockItem.setAgeMin(25);
        mockItem.setAgeMax(40);
        mockItem.setHairColor("Black");
        mockItem.setEyeColor("Brown");
        mockResponse.setItems(List.of(mockItem));
    }

    @Test
    void testFetchWantedPersons_Success() {
        // Mock the external API call
        when(restTemplate.getForObject(anyString(), eq(FbiApiResponse.class)))
                .thenReturn(mockResponse);

        // Call service method
        List<WantedPerson> result = fbiService.fetchWantedPersons(1, "John", null, "American", null, null, null, null, null).getData();

        // Validate response
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getTitle());

        // Verify that the RestTemplate was called once
        verify(restTemplate, times(1)).getForObject(anyString(), eq(FbiApiResponse.class));
    }

    @Test
    void testFetchWantedPersons_EmptyResponse() {
        when(restTemplate.getForObject(anyString(), eq(FbiApiResponse.class)))
                .thenReturn(null);

        List<WantedPerson> result = fbiService.fetchWantedPersons(1, null, null, null, null, null, null, null, null).getData();

        assertNotNull(result);
        assertTrue(result.isEmpty());

        // Verify API was called once
        verify(restTemplate, times(1)).getForObject(anyString(), eq(FbiApiResponse.class));
    }

    @Test
    void testFetchWantedPersons_FilterByNationality() {
        when(restTemplate.getForObject(anyString(), eq(FbiApiResponse.class)))
                .thenReturn(mockResponse);

        List<WantedPerson> result = fbiService.fetchWantedPersons(1, null, null, "American", null, null, null, null, null).getData();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("American", result.get(0).getNationality());

        // Verify the external call
        verify(restTemplate, times(1)).getForObject(anyString(), eq(FbiApiResponse.class));
    }

    @Test
    void testFetchWantedPersons_NoMatchingNationality() {
        when(restTemplate.getForObject(anyString(), eq(FbiApiResponse.class)))
                .thenReturn(mockResponse);

        List<WantedPerson> result = fbiService.fetchWantedPersons(1, null, null, "Russian", null, null, null, null, null).getData();

        assertNotNull(result);
        assertTrue(result.isEmpty()); // Should return an empty list

        // Verify API was called once
        verify(restTemplate, times(1)).getForObject(anyString(), eq(FbiApiResponse.class));
    }

    @Test
    void testFetchWantedPersons_FilterByAgeRange() {
        when(restTemplate.getForObject(anyString(), eq(FbiApiResponse.class)))
                .thenReturn(mockResponse);

        List<WantedPerson> result = fbiService.fetchWantedPersons(1, null, null, null, null, 20, 45, null, null).getData();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.get(0).getAgeMin() >= 20 && result.get(0).getAgeMax() <= 45);

        // Verify API was called once
        verify(restTemplate, times(1)).getForObject(anyString(), eq(FbiApiResponse.class));
    }

    @Test
    void testFetchWantedPersons_FilterByHairColor() {
        when(restTemplate.getForObject(anyString(), eq(FbiApiResponse.class)))
                .thenReturn(mockResponse);

        List<WantedPerson> result = fbiService.fetchWantedPersons(1, null, null, null, null, null, null, "Black", null).getData();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Black", result.get(0).getHairColor());

        // Verify API was called once
        verify(restTemplate, times(1)).getForObject(anyString(), eq(FbiApiResponse.class));
    }

    @Test
    void testFetchWantedPersons_FilterByEyeColor() {
        when(restTemplate.getForObject(anyString(), eq(FbiApiResponse.class)))
                .thenReturn(mockResponse);

        List<WantedPerson> result = fbiService.fetchWantedPersons(1, null, null, null, null, null, null, null, "Brown").getData();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Brown", result.get(0).getEyeColor());

        // Verify API was called once
        verify(restTemplate, times(1)).getForObject(anyString(), eq(FbiApiResponse.class));
    }

    @Test
    void testFetchWantedPersons_NoMatchingFilters() {
        when(restTemplate.getForObject(anyString(), eq(FbiApiResponse.class)))
                .thenReturn(mockResponse);

        List<WantedPerson> result = fbiService.fetchWantedPersons(1, null, "White", "Canadian", "Female", 50, 60, "Blonde", "Blue").getData();

        assertNotNull(result);
        assertTrue(result.isEmpty());

        // Verify API was called once
        verify(restTemplate, times(1)).getForObject(anyString(), eq(FbiApiResponse.class));
    }
}
