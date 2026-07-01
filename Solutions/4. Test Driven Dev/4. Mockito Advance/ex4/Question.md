Exercise 4: Mocking Network Interactions 
You need to test a service that interacts with network resources. 
Steps: 
4. 1. Create a mock network client using Mockito. 
5. 2. Stub the network client methods to simulate network interactions. 
6. 3. Write a test to verify the service logic using the mocked network client. 
Solution Code: 
 
import static org.mockito.Mockito.*; 
import org.junit.jupiter.api.Test; 
import static org.junit.jupiter.api.Assertions.*; 
 
public class NetworkServiceTest { 
    @Test 
    public void testServiceWithMockNetworkClient() { 
        NetworkClient mockNetworkClient = mock(NetworkClient.class); 
        when(mockNetworkClient.connect()).thenReturn("Mock Connection"); 
 
        NetworkService networkService = new NetworkService(mockNetworkClient); 
        String result = networkService.connectToServer(); 
 
        assertEquals("Connected to Mock Connection", result); 
    } 
}