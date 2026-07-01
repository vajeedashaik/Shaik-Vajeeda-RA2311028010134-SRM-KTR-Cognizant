Exercise 3: Mocking File I/O 
You need to test a service that reads from and writes to files. 
Steps: 
1. Create a mock file reader and writer using Mockito. 
2. Stub the file reader and writer methods to simulate file operations. 
3. Write a test to verify the service logic using the mocked file reader and writer. 
Solution Code: 
 
import static org.mockito.Mockito.*; 
import org.junit.jupiter.api.Test; 
import static org.junit.jupiter.api.Assertions.*; 
 
public class FileServiceTest { 
    @Test 
    public void testServiceWithMockFileIO() { 
        FileReader mockFileReader = mock(FileReader.class); 
        FileWriter mockFileWriter = mock(FileWriter.class); 
        when(mockFileReader.read()).thenReturn("Mock File Content"); 
 
        FileService fileService = new FileService(mockFileReader, mockFileWriter); 
        String result = fileService.processFile(); 
 
        assertEquals("Processed Mock File Content", result); 
    } 
}