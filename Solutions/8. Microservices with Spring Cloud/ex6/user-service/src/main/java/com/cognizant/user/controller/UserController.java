package com.cognizant.user.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.cognizant.user.model.User;

// SB3 Exercise 1: User REST API — CRUD + fetches orders from order-service
@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    // In-memory store for demo (no DB)
    private static final List<User> USERS = Arrays.asList(
        new User(1L, "Alice", "alice@example.com"),
        new User(2L, "Bob", "bob@example.com")
    );

    @Autowired
    private WebClient.Builder webClientBuilder;

    @GetMapping
    public List<User> getAllUsers() {
        LOGGER.info("START - getAllUsers");
        LOGGER.info("END");
        return USERS;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        LOGGER.info("START - getUser: {}", id);
        User user = USERS.stream().filter(u -> u.getId().equals(id)).findFirst()
                .orElseThrow(() -> new RuntimeException("User not found: " + id));
        LOGGER.info("END");
        return user;
    }

    // Fetch orders for a user from order-service via WebClient
    @GetMapping("/{id}/orders")
    public List<Map> getUserOrders(@PathVariable Long id) {
        LOGGER.info("START - getUserOrders: {}", id);
        // Calls order-service via Eureka load-balanced URL
        List<Map> orders = webClientBuilder.build()
                .get()
                .uri("http://order-service/orders/user/" + id)
                .retrieve()
                .bodyToFlux(Map.class)
                .collectList()
                .block();
        LOGGER.info("END");
        return orders;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        LOGGER.info("START - createUser: {}", user.getName());
        LOGGER.info("END");
        return user;
    }
}
