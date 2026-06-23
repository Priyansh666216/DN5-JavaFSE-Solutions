package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MyServiceTest {

    private ExternalApi mockApi;
    private MyService service;

    @BeforeEach
    public void setUp() {
        // Step 1: Create a mock object
        mockApi = Mockito.mock(ExternalApi.class);
        service  = new MyService(mockApi);
    }

    // -------------------------------------------------------
    // Test 1: Basic interaction — verify method was called
    // -------------------------------------------------------
    @Test
    public void testVerifyInteraction() {
        // Step 2: Call the method
        service.fetchData();

        // Step 3: Verify getData() was called exactly once
        verify(mockApi).getData();
    }

    // -------------------------------------------------------
    // Test 2: Verify method called with specific argument
    // -------------------------------------------------------
    @Test
    public void testVerifyCalledWithSpecificArgument() {
        when(mockApi.getUserById(42)).thenReturn("Priyanshu");

        // Act
        String result = service.fetchUser(42);

        // Verify it was called with exactly 42, not any other id
        verify(mockApi).getUserById(42);
        assertEquals("Priyanshu", result);
    }

    // -------------------------------------------------------
    // Test 3: Verify method called exact number of times
    // -------------------------------------------------------
    @Test
    public void testVerifyCallCount() {
        service.fetchData();
        service.fetchData();
        service.fetchData();

        // Verify getData() was called exactly 3 times
        verify(mockApi, times(3)).getData();
    }

    // -------------------------------------------------------
    // Test 4: Verify method was never called
    // -------------------------------------------------------
    @Test
    public void testVerifyNeverCalled() {
        service.fetchData();

        // fetchData() only calls getData() — getUserById should never be touched
        verify(mockApi, never()).getUserById(anyInt());
    }

    // -------------------------------------------------------
    // Test 5: Verify method called at least / at most N times
    // -------------------------------------------------------
    @Test
    public void testVerifyAtLeastAndAtMost() {
        service.fetchData();
        service.fetchData();

        verify(mockApi, atLeast(1)).getData();   // called at least once
        verify(mockApi, atMost(5)).getData();    // called no more than 5 times
    }

    // -------------------------------------------------------
    // Test 6: Verify multiple methods called with specific args
    // -------------------------------------------------------
    @Test
    public void testVerifyMultipleInteractions() {
        service.updateAndNotify(7, "active");

        // Both methods should have been called with exact arguments
        verify(mockApi).updateRecord(7, "active");
        verify(mockApi).sendNotification("Record 7 updated");
    }

    // -------------------------------------------------------
    // Test 7: Verify using argument matchers
    // -------------------------------------------------------
    @Test
    public void testVerifyWithArgumentMatchers() {
        service.notifyUser("Hello!");

        // anyString() matches any string argument
        verify(mockApi).sendNotification(anyString());
    }

    // -------------------------------------------------------
    // Test 8: Verify no more interactions after expected calls
    // -------------------------------------------------------
    @Test
    public void testNoMoreUnexpectedInteractions() {
        service.fetchData();

        verify(mockApi).getData();

        // Fails if any other method on mockApi was called unexpectedly
        verifyNoMoreInteractions(mockApi);
    }
}
