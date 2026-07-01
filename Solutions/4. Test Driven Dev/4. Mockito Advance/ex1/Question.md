Exercise 1: Mocking Databases and Repositories 
You need to test a service that interacts with a database repository. 
Steps: 
1. Create a mock repository using Mockito. 
2. Stub the repository methods to return predefined data. 
3.  Write a test to verify the service logic using the mocked repository. 
Solution Code: 
 
import static org.mockito.Mockito.*; 
import org.junit.jupiter.api.Test; 
import static org.junit.jupiter.api.Assertions.*; 
 
public class ServiceTest { 
    @Test 
    public void testServiceWithMockRepository() { 
        Repository mockRepository = mock(Repository.class); 
        when(mockRepository.getData()).thenReturn("Mock Data"); 
 
        Service service = new Service(mockRepository); 
        String result = service.processData(); 
 
        assertEquals("Processed Mock Data", result); 
    } 
} 