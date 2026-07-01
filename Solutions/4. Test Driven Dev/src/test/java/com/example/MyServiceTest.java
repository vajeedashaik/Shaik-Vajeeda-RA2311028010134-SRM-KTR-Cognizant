package com.example;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.*;

// Mockito Exercises 1-7
public class MyServiceTest {

    // Exercise 1: Mocking and Stubbing
    @Test
    void testExternalApi_mockAndStub() {
        // Arrange
        ExternalApi mockApi = mock(ExternalApi.class);
        when(mockApi.getData()).thenReturn("Mock Data");

        MyService service = new MyService(mockApi);

        // Act
        String result = service.fetchData();

        // Assert
        assertEquals("Mock Data", result);
    }

    // Exercise 2: Verifying Interactions
    @Test
    void testVerifyInteraction() {
        ExternalApi mockApi = mock(ExternalApi.class);
        MyService service = new MyService(mockApi);

        service.fetchData();

        // Verify getData() was called exactly once
        verify(mockApi).getData();
        verify(mockApi, times(1)).getData();
    }

    // Exercise 3: Argument Matching
    @Test
    void testArgumentMatching() {
        ExternalApi mockApi = mock(ExternalApi.class);
        MyService service = new MyService(mockApi);

        service.processRequest("hello");

        // Verify with exact argument
        verify(mockApi).processRequest("hello");

        // Verify with any String argument matcher
        verify(mockApi).processRequest(anyString());

        // Verify with startsWith matcher
        verify(mockApi).processRequest(startsWith("he"));
    }

    // Exercise 4: Handling Void Methods
    @Test
    void testVoidMethod() {
        ExternalApi mockApi = mock(ExternalApi.class);

        // Stub void method - doNothing is default, but explicit for clarity
        doNothing().when(mockApi).sendData(anyString());

        MyService service = new MyService(mockApi);
        service.sendData("test payload");

        // Verify void method was called with expected argument
        verify(mockApi).sendData("test payload");
    }

    // Exercise 5: Mocking and Stubbing with Multiple Returns
    @Test
    void testMultipleReturns() {
        ExternalApi mockApi = mock(ExternalApi.class);
        when(mockApi.getData())
            .thenReturn("First Call")
            .thenReturn("Second Call")
            .thenReturn("Third Call");

        MyService service = new MyService(mockApi);

        assertEquals("First Call",  service.fetchData());
        assertEquals("Second Call", service.fetchData());
        assertEquals("Third Call",  service.fetchData());
        // Subsequent calls return last stubbed value
        assertEquals("Third Call",  service.fetchData());
    }

    // Exercise 6: Verifying Interaction Order
    @Test
    void testInteractionOrder() {
        ExternalApi mockApi = mock(ExternalApi.class);
        MyService service = new MyService(mockApi);

        service.fetchData();
        service.sendData("payload");
        service.processRequest("req");

        InOrder inOrder = inOrder(mockApi);
        inOrder.verify(mockApi).getData();
        inOrder.verify(mockApi).sendData("payload");
        inOrder.verify(mockApi).processRequest("req");
    }

    // Exercise 7: Handling Void Methods with Exceptions
    @Test
    void testVoidMethodThrowsException() {
        ExternalApi mockApi = mock(ExternalApi.class);

        // Stub void method to throw
        doThrow(new RuntimeException("Send failed")).when(mockApi).sendData("bad data");

        MyService service = new MyService(mockApi);

        assertThrows(RuntimeException.class, () -> service.sendData("bad data"));

        verify(mockApi).sendData("bad data");
    }
}
