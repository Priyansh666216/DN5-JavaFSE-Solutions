package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MyServiceTest {

    private ExternalApi mockApi;
    private MyService service;

    // Runs before each test — creates a fresh mock and injects it
    @BeforeEach
    public void setUp() {
        // Step 1: Create a mock object for ExternalApi
        // Mockito creates a fake implementation — no real HTTP calls are made
        mockApi = Mockito.mock(ExternalApi.class);

        // Inject the mock into the service
        service = new MyService(mockApi);
    }

    // -------------------------------------------------------
    // Test 1: Basic stubbing with thenReturn
    // -------------------------------------------------------
    @Test
    public void testFetchData() {
        // Step 2: Stub — tell the mock what to return when getData() is called
        when(mockApi.getData()).thenReturn("Mock Data");

        // Step 3: Call the service method (uses the mock internally)
        String result = service.fetchData();

        // Assert the stubbed value came back correctly
        assertEquals("Mock Data", result);

        // Verify getData() was actually called once
        verify(mockApi, times(1)).getData();
    }

    // -------------------------------------------------------
    // Test 2: Stubbing with method arguments
    // -------------------------------------------------------
    @Test
    public void testFetchUserById() {
        // Stub different IDs to return different responses
        when(mockApi.getUserById(1)).thenReturn("Alice");
        when(mockApi.getUserById(2)).thenReturn("Bob");

        String user1 = service.fetchUser(1);
        String user2 = service.fetchUser(2);

        assertEquals("Alice", user1);
        assertEquals("Bob", user2);

        // Verify both calls happened
        verify(mockApi).getUserById(1);
        verify(mockApi).getUserById(2);
    }

    // -------------------------------------------------------
    // Test 3: Stubbing a boolean return value (service UP)
    // -------------------------------------------------------
    @Test
    public void testServiceStatusUp() {
        when(mockApi.isServiceAvailable()).thenReturn(true);

        String status = service.checkStatus();

        assertEquals("Service is UP", status);
    }

    // -------------------------------------------------------
    // Test 4: Stubbing a boolean return value (service DOWN)
    // -------------------------------------------------------
    @Test
    public void testServiceStatusDown() {
        when(mockApi.isServiceAvailable()).thenReturn(false);

        String status = service.checkStatus();

        assertEquals("Service is DOWN", status);
    }

    // -------------------------------------------------------
    // Test 5: Stubbing to throw an exception
    // -------------------------------------------------------
    @Test
    public void testFetchDataThrowsException() {
        // Stub getData() to simulate an API failure
        when(mockApi.getData()).thenThrow(new RuntimeException("API timeout"));

        assertThrows(RuntimeException.class, () -> service.fetchData());
    }

    // -------------------------------------------------------
    // Test 6: Verify a method was NEVER called
    // -------------------------------------------------------
    @Test
    public void testFetchDataNeverCallsGetUser() {
        when(mockApi.getData()).thenReturn("Some Data");

        service.fetchData();

        // fetchData() should only call getData(), never getUserById()
        verify(mockApi, never()).getUserById(anyInt());
    }
}
