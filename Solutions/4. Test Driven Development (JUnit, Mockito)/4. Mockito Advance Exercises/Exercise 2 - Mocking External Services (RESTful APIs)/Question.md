Exercise 2: Mocking External Services (RESTful APIs) 
You need to test a service that calls an external RESTful API. 
Steps: 
1. Create a mock REST client using Mockito. 
2. Stub the REST client methods to return predefined responses. 
3. Write a test to verify the service logic using the mocked REST client. 
Solution Code: 
 
import static org.mockito.Mockito.*; 
import org.junit.jupiter.api.Test; 
import static org.junit.jupiter.api.Assertions.*; 
 
public class ApiServiceTest { 
    @Test 
    public void testServiceWithMockRestClient() { 
        RestClient mockRestClient = mock(RestClient.class); 
        when(mockRestClient.getResponse()).thenReturn("Mock Response"); 
 
        ApiService apiService = new ApiService(mockRestClient); 
        String result = apiService.fetchData(); 
 
        assertEquals("Fetched Mock Response", result); 
    } 
}