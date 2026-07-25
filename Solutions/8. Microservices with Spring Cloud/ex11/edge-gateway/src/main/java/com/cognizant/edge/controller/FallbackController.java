package com.cognizant.edge.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// LB PDF Exercise 3: graceful response returned when a downstream circuit breaker is open.
// No HTTP method restriction: the gateway's CircuitBreaker filter forwards to this endpoint
// preserving the original request's method (GET, POST, etc.), so it must accept all of them.
@RestController
public class FallbackController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FallbackController.class);

    @RequestMapping("/fallback")
    public Map<String, String> fallback() {
        LOGGER.warn("Circuit breaker OPEN — serving fallback response");
        Map<String, String> response = new HashMap<>();
        response.put("status", "UNAVAILABLE");
        response.put("message", "The requested service is temporarily unavailable. Please try again later.");
        return response;
    }
}
