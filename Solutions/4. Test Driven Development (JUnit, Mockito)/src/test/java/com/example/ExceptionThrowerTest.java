package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// JUnit Advanced - Exercise 4: Exception Testing
public class ExceptionThrowerTest {

    private final ExceptionThrower thrower = new ExceptionThrower();

    @Test
    void testThrowException_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, thrower::throwException);
    }

    @Test
    void testThrowException_messageIsCorrect() {
        IllegalArgumentException ex = assertThrows(
            IllegalArgumentException.class,
            thrower::throwException
        );
        assertEquals("Expected exception", ex.getMessage());
    }

    @Test
    void testDivideByZero_throwsArithmeticException() {
        assertThrows(ArithmeticException.class, () -> thrower.divideByZero(10));
    }

    @Test
    void testNoException_doesNotThrow() {
        // Verify normal code path doesn't throw
        Calculator calc = new Calculator();
        assertDoesNotThrow(() -> calc.add(1, 2));
    }
}
