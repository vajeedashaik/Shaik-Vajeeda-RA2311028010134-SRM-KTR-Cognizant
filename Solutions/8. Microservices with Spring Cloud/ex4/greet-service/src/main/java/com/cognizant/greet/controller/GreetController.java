package com.cognizant.greet.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// API Gateway handson: accessed via http://localhost:9090/greet-service/greet
@RestController
public class GreetController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GreetController.class);

    @GetMapping("/greet")
    public String greet() {
        LOGGER.info("START");
        LOGGER.info("END");
        return "Hello World";
    }
}
