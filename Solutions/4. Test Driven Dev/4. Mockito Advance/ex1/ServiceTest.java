package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// Advanced Mockito - Exercise 1: Mocking Databases and Repositories
public class ServiceTest {

    @Test
    void testServiceWithMockRepository() {
        // Arrange
        Repository mockRepository = mock(Repository.class);
        when(mockRepository.getData()).thenReturn("Mock Data");

        Service service = new Service(mockRepository);

        // Act
        String result = service.processData();

        // Assert
        assertEquals("Processed Mock Data", result);
        verify(mockRepository).getData();
    }

    @Test
    void testServiceWithMockRepository_saveData() {
        Repository mockRepository = mock(Repository.class);
        Service service = new Service(mockRepository);

        // void method - just verify it's called
        service.processData(); // calls getData internally
        verify(mockRepository, times(1)).getData();
        verify(mockRepository, never()).saveData(anyString());
    }
}
