package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Exercise 3: Using Different Appenders (Console + File via logback.xml)
public class AppendersExample {

    private static final Logger logger = LoggerFactory.getLogger(AppendersExample.class);

    public static void main(String[] args) {
        logger.debug("Debug message - goes to console and file appender");
        logger.info("Info message - logged to both console and file");
        logger.warn("Warning message - check logback.xml for appender config");
        logger.error("Error message - written to app.log file");
    }
}
