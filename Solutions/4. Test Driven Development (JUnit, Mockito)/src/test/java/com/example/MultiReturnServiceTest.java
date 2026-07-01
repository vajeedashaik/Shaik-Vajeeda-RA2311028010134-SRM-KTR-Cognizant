package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// Advanced Mockito - Exercise 5: Mocking Multiple Return Values
public class MultiReturnServiceTest {

    @Test
    void testServiceWithMultipleReturnValues() {
        // Arrange
        Repository mockRepository = mock(Repository.class);
        when(mockRepository.getData())
            .thenReturn("First Mock Data")
            .thenReturn("Second Mock Data");

        Service service = new Service(mockRepository);

        // Act
        String firstResult  = service.processData();
        String secondResult = service.processData();

        // Assert
        assertEquals("Processed First Mock Data",  firstResult);
        assertEquals("Processed Second Mock Data", secondResult);

        // Subsequent calls after stubbing exhausted return last value
        assertEquals("Processed Second Mock Data", service.processData());

        verify(mockRepository, times(3)).getData();
    }
}
