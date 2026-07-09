package com.cognizant.payment.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

// SB3 Exercise 4: Calls slow third-party API with Circuit Breaker protection
@Service
public class ThirdPartyPaymentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThirdPartyPaymentService.class);

    // Circuit breaker name matches application.properties config key
    @CircuitBreaker(name = "thirdPartyPayment", fallbackMethod = "paymentFallback")
    public String processPayment(String orderId, Double amount) {
        LOGGER.info("START - processPayment: orderId={}, amount={}", orderId, amount);

        // Simulate a slow/unreliable third-party API call
        simulateThirdPartyApiCall();

        String result = "Payment processed for order " + orderId + " amount: " + amount;
        LOGGER.info("END - {}", result);
        return result;
    }

    // Fallback when circuit is open or call fails
    public String paymentFallback(String orderId, Double amount, Throwable t) {
        LOGGER.warn("FALLBACK triggered for orderId={}: {}", orderId, t.getMessage());
        return "Payment service temporarily unavailable. Order " + orderId + " queued for retry.";
    }

    private void simulateThirdPartyApiCall() {
        // Simulates latency — in real scenario this calls an external payment gateway
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Payment API call interrupted");
        }
    }
}
