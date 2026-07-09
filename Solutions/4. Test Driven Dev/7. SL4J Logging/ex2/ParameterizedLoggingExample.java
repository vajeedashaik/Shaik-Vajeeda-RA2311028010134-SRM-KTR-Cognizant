package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Exercise 2: Parameterized Logging
public class ParameterizedLoggingExample {

    private static final Logger logger = LoggerFactory.getLogger(ParameterizedLoggingExample.class);

    public static void main(String[] args) {
        String user = "Alice";
        int age = 30;
        double balance = 1500.75;

        // Parameterized logging avoids string concatenation when log level is disabled
        logger.info("User {} has logged in", user);
        logger.info("User {} is {} years old", user, age);
        logger.debug("Account balance for {}: {}", user, balance);
        logger.warn("User {} has low balance: {}", user, balance);
        logger.error("Failed to process transaction for user {} with balance {}", user, balance);
    }
}
