package com.example;

public class ExceptionThrower {

    public void throwException() {
        throw new IllegalArgumentException("Expected exception");
    }

    public int divideByZero(int a) {
        return a / 0;
    }
}
