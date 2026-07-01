Exercise 5: Mocking Multiple Return Values 
You need to test a service that calls a method multiple times with 
different return values. 
Steps: 
1. Create a mock object using Mockito. 
2. Stub the method to return different values on consecutive calls. 
3. Write a test to verify the service logic using the mocked object. 
Solution Code: 
 
import static org.mockito.Mockito.*; 
import org.junit.jupiter.api.Test; 
import static org.junit.jupiter.api.Assertions.*; 
 
public class MultiReturnServiceTest { 
    @Test 
    public void testServiceWithMultipleReturnValues() { 
        Repository mockRepository = mock(Repository.class); 
        when(mockRepository.getData()) 
            .thenReturn("First Mock Data") 
            .thenReturn("Second Mock Data"); 
 
        Service service = new Service(mockRepository); 
        String firstResult = service.processData(); 
        String secondResult = service.processData(); 
 
        assertEquals("Processed First Mock Data", firstResult); 
        assertEquals("Processed Second Mock Data", secondResult); 
    } 
}