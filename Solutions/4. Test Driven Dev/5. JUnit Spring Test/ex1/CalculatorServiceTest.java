package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

// Spring Test - Exercise 1: Basic Unit Test for a Service Method
// Spring Test - Exercise 9: Parameterized Test with JUnit
public class CalculatorServiceTest {

    private final CalculatorService calculatorService = new CalculatorService();

    // Exercise 1
    @Test
    void testAdd() {
        assertEquals(5, calculatorService.add(2, 3));
    }

    @Test
    void testSubtract() {
        assertEquals(1, calculatorService.subtract(3, 2));
    }

    @Test
    void testMultiply() {
        assertEquals(12, calculatorService.multiply(3, 4));
    }

    // Exercise 9: Parameterized Test
    @ParameterizedTest
    @CsvSource({
        "1, 2, 3",
        "0, 0, 0",
        "-1, 1, 0",
        "10, 20, 30",
        "100, 200, 300"
    })
    void testAdd_parameterized(int a, int b, int expected) {
        assertEquals(expected, calculatorService.add(a, b));
    }
}
