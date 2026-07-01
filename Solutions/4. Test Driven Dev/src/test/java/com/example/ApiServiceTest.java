package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// Advanced Mockito - Exercise 2: Mocking External Services (RESTful APIs)
public class ApiServiceTest {

    @Test
    void testServiceWithMockRestClient() {
        // Arrange
        RestClient mockRestClient = mock(RestClient.class);
        when(mockRestClient.getResponse()).thenReturn("Mock Response");

        ApiService apiService = new ApiService(mockRestClient);

        // Act
        String result = apiService.fetchData();

        // Assert
        assertEquals("Fetched Mock Response", result);
        verify(mockRestClient).getResponse();
    }

    @Test
    void testServiceWithMockRestClient_errorResponse() {
        RestClient mockRestClient = mock(RestClient.class);
        when(mockRestClient.getResponse()).thenThrow(new RuntimeException("Connection refused"));

        ApiService apiService = new ApiService(mockRestClient);

        assertThrows(RuntimeException.class, apiService::fetchData);
    }
}
