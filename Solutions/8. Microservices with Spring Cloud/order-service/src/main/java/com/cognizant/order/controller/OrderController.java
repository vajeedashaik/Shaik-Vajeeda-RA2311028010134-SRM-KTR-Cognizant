package com.cognizant.order.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.order.model.Order;

// SB3 Exercise 1: Order REST API — GET all orders, GET by user, POST new order
@RestController
@RequestMapping("/orders")
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    private static final List<Order> ORDERS = Arrays.asList(
        new Order(1L, 1L, "Laptop", 1200.00, "DELIVERED"),
        new Order(2L, 1L, "Phone", 800.00, "SHIPPED"),
        new Order(3L, 2L, "Tablet", 500.00, "PROCESSING")
    );

    @GetMapping
    public List<Order> getAllOrders() {
        LOGGER.info("START");
        LOGGER.info("END");
        return ORDERS;
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Long id) {
        LOGGER.info("START - getOrder: {}", id);
        Order order = ORDERS.stream().filter(o -> o.getId().equals(id)).findFirst()
                .orElseThrow(() -> new RuntimeException("Order not found: " + id));
        LOGGER.info("END");
        return order;
    }

    // Called by user-service: GET /orders/user/{userId}
    @GetMapping("/user/{userId}")
    public List<Order> getOrdersByUser(@PathVariable Long userId) {
        LOGGER.info("START - getOrdersByUser: {}", userId);
        List<Order> userOrders = ORDERS.stream()
                .filter(o -> o.getUserId().equals(userId))
                .collect(Collectors.toList());
        LOGGER.info("END");
        return userOrders;
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        LOGGER.info("START - createOrder: {}", order.getProduct());
        LOGGER.info("END");
        return order;
    }
}
