package com.cognizant.payment.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.payment.service.ThirdPartyPaymentService;

// SB3 Exercise 4: Payment REST API — triggers circuit-breaker-protected payment call
@RestController
public class PaymentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentController.class);

    @Autowired
    private ThirdPartyPaymentService paymentService;

    // POST /payments/process?orderId=123&amount=500
    @PostMapping("/payments/process")
    public Map<String, String> processPayment(
            @RequestParam String orderId,
            @RequestParam Double amount) {
        LOGGER.info("START - processPayment: orderId={}", orderId);
        String result = paymentService.processPayment(orderId, amount);
        Map<String, String> response = new HashMap<>();
        response.put("status", result);
        LOGGER.info("END");
        return response;
    }

    // GET /payments/status/{orderId} — check payment status
    @GetMapping("/payments/status/{orderId}")
    public Map<String, String> getPaymentStatus(@PathVariable String orderId) {
        LOGGER.info("START - getPaymentStatus: {}", orderId);
        Map<String, String> response = new HashMap<>();
        response.put("orderId", orderId);
        response.put("status", "COMPLETED");
        LOGGER.info("END");
        return response;
    }
}
