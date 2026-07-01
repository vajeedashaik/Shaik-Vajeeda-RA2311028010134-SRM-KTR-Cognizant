package com.example;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

// JUnit Advanced - Exercise 3: Test Execution Order
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderedTests {

    private static int counter = 0;

    @Test
    @Order(1)
    void firstTest() {
        counter++;
        assertEquals(1, counter);
        System.out.println("Test 1 executed. Counter = " + counter);
    }

    @Test
    @Order(2)
    void secondTest() {
        counter++;
        assertEquals(2, counter);
        System.out.println("Test 2 executed. Counter = " + counter);
    }

    @Test
    @Order(3)
    void thirdTest() {
        counter++;
        assertEquals(3, counter);
        System.out.println("Test 3 executed. Counter = " + counter);
    }
}
