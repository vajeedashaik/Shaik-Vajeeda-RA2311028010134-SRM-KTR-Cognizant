package com.example;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

// JUnit Basic - Exercise 4: AAA Pattern + @BeforeEach/@AfterEach + @BeforeAll/@AfterAll
public class AAAPatternTest {

    private Calculator calculator;

    @BeforeAll
    static void initAll() {
        System.out.println("@BeforeAll: runs once before all tests in this class");
    }

    @BeforeEach
    void setUp() {
        // Arrange shared state
        calculator = new Calculator();
        System.out.println("@BeforeEach: fresh Calculator created before each test");
    }

    @AfterEach
    void tearDown() {
        calculator = null;
        System.out.println("@AfterEach: Calculator cleaned up after each test");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("@AfterAll: runs once after all tests in this class");
    }

    @Test
    void testAddition_AAAPattern() {
        // Arrange
        int a = 10;
        int b = 5;

        // Act
        int result = calculator.add(a, b);

        // Assert
        assertEquals(15, result);
    }

    @Test
    void testSubtraction_AAAPattern() {
        // Arrange
        int a = 10;
        int b = 3;

        // Act
        int result = calculator.subtract(a, b);

        // Assert
        assertEquals(7, result);
    }

    @Test
    void testMultiplication_AAAPattern() {
        // Arrange
        int a = 4;
        int b = 5;

        // Act
        int result = calculator.multiply(a, b);

        // Assert
        assertEquals(20, result);
    }
}
