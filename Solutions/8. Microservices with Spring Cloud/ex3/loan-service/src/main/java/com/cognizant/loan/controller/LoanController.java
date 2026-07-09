package com.cognizant.loan.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

// Handson: GET /loans/{number} — returns dummy loan details
@RestController
public class LoanController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoanController.class);

    // Sample response: { number: "H00987987972342", type: "car", loan: 400000, emi: 3258, tenure: 18 }
    @GetMapping("/loans/{number}")
    public Map<String, Object> getLoan(@PathVariable String number) {
        LOGGER.info("START - getLoan: {}", number);
        Map<String, Object> loan = new HashMap<>();
        loan.put("number", number);
        loan.put("type", "car");
        loan.put("loan", 400000);
        loan.put("emi", 3258);
        loan.put("tenure", 18);
        LOGGER.info("END");
        return loan;
    }
}
