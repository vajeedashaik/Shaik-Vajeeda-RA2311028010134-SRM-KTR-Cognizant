package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.*;

// JUnit Advanced - Exercise 5: Timeout and Performance Testing
public class PerformanceTesterTest {

    private final PerformanceTester tester = new PerformanceTester();

    // @Timeout: test fails if it exceeds 2 seconds
    @Test
    @Timeout(value = 2, unit = TimeUnit.SECONDS)
    void testPerformTask_completesWithinTimeout() {
        String result = tester.performTask();
        assertNotNull(result);
        assertTrue(result.startsWith("Result:"));
    }

    // Using assertTimeout for inline timeout assertion
    @Test
    void testPerformTask_assertTimeout() {
        assertTimeout(
            java.time.Duration.ofSeconds(2),
            () -> tester.performTask()
        );
    }

    // assertTimeoutPreemptively aborts execution if it exceeds timeout
    @Test
    void testPerformTask_assertTimeoutPreemptively() {
        String result = assertTimeoutPreemptively(
            java.time.Duration.ofSeconds(2),
            () -> tester.performTask()
        );
        assertNotNull(result);
    }
}
