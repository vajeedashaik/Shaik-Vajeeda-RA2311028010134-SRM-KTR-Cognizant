package com.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

// JUnit Advanced - Exercise 1: Parameterized Tests
public class EvenCheckerTest {

    private final EvenChecker checker = new EvenChecker();

    // Test that even numbers return true
    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6, 8, 100, 0, -2})
    void testIsEven_withEvenNumbers(int number) {
        assertTrue(checker.isEven(number));
    }

    // Test that odd numbers return false
    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 7, 99, -1})
    void testIsEven_withOddNumbers(int number) {
        assertFalse(checker.isEven(number));
    }

    // Using CsvSource to test input and expected result together
    @ParameterizedTest
    @CsvSource({
        "2, true",
        "3, false",
        "10, true",
        "11, false"
    })
    void testIsEven_withCsvSource(int number, boolean expected) {
        assertEquals(expected, checker.isEven(number));
    }
}
