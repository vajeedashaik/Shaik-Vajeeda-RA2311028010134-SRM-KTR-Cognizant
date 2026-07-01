package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// Advanced Mockito - Exercise 3: Mocking File I/O
public class FileServiceTest {

    @Test
    void testServiceWithMockFileIO() {
        // Arrange
        FileReader mockFileReader = mock(FileReader.class);
        FileWriter mockFileWriter = mock(FileWriter.class);
        when(mockFileReader.read()).thenReturn("Mock File Content");

        FileService fileService = new FileService(mockFileReader, mockFileWriter);

        // Act
        String result = fileService.processFile();

        // Assert
        assertEquals("Processed Mock File Content", result);
        verify(mockFileReader).read();
        verify(mockFileWriter).write("Mock File Content");
    }

    @Test
    void testServiceWithMockFileIO_readerThrowsException() {
        FileReader mockFileReader = mock(FileReader.class);
        FileWriter mockFileWriter = mock(FileWriter.class);
        when(mockFileReader.read()).thenThrow(new RuntimeException("File not found"));

        FileService fileService = new FileService(mockFileReader, mockFileWriter);

        assertThrows(RuntimeException.class, fileService::processFile);
        verify(mockFileWriter, never()).write(anyString());
    }
}
