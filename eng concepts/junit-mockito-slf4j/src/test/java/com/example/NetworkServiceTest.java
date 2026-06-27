package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// Advanced Mockito - Exercise 4: Mocking Network Interactions
public class NetworkServiceTest {

    @Test
    void testServiceWithMockNetworkClient() {
        // Arrange
        NetworkClient mockNetworkClient = mock(NetworkClient.class);
        when(mockNetworkClient.connect()).thenReturn("Mock Connection");

        NetworkService networkService = new NetworkService(mockNetworkClient);

        // Act
        String result = networkService.connectToServer();

        // Assert
        assertEquals("Connected to Mock Connection", result);
        verify(mockNetworkClient).connect();
    }

    @Test
    void testServiceWithMockNetworkClient_connectionFails() {
        NetworkClient mockNetworkClient = mock(NetworkClient.class);
        when(mockNetworkClient.connect()).thenThrow(new RuntimeException("Network unreachable"));

        NetworkService networkService = new NetworkService(mockNetworkClient);

        assertThrows(RuntimeException.class, networkService::connectToServer);
    }
}
