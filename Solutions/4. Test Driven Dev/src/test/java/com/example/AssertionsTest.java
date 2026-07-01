package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// JUnit Basic - Exercise 3: Assertions in JUnit
public class AssertionsTest {

    @Test
    void testAssertions() {
        // assertEquals
        assertEquals(5, 2 + 3);

        // assertTrue / assertFalse
        assertTrue(5 > 3);
        assertFalse(5 < 3);

        // assertNull / assertNotNull
        assertNull(null);
        assertNotNull(new Object());

        // assertArrayEquals
        assertArrayEquals(new int[]{1, 2, 3}, new int[]{1, 2, 3});

        // assertSame / assertNotSame
        String s = "hello";
        assertSame(s, s);
        assertNotSame("a", "b");

        // assertThrows
        assertThrows(ArithmeticException.class, () -> {
            int result = 1 / 0;
        });

        // assertAll - groups multiple assertions
        assertAll("calculator checks",
            () -> assertEquals(4, 2 + 2),
            () -> assertTrue(10 > 5),
            () -> assertNotNull("test")
        );
    }
}
