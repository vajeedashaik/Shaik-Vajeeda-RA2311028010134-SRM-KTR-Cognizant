package com.cognizant.inventory.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.cognizant.inventory.model.InventoryItem;

// SB3 Exercise 2: Inventory REST API — stock tracking per product
@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryController.class);

    private static final List<InventoryItem> INVENTORY = Arrays.asList(
        new InventoryItem(1L, "Laptop", 50, "Warehouse-A"),
        new InventoryItem(2L, "Phone", 100, "Warehouse-B"),
        new InventoryItem(3L, "Desk", 20, "Warehouse-A")
    );

    @Autowired
    private WebClient.Builder webClientBuilder;

    @GetMapping
    public List<InventoryItem> getAllInventory() {
        LOGGER.info("START");
        LOGGER.info("END");
        return INVENTORY;
    }

    @GetMapping("/{productId}")
    public InventoryItem getInventory(@PathVariable Long productId) {
        LOGGER.info("START - getInventory: {}", productId);
        InventoryItem item = INVENTORY.stream().filter(i -> i.getProductId().equals(productId)).findFirst()
                .orElseThrow(() -> new RuntimeException("Inventory not found for product: " + productId));
        LOGGER.info("END");
        return item;
    }

    // Enrich inventory with product details from product-service
    @GetMapping("/{productId}/details")
    public Map<String, Object> getInventoryWithProductDetails(@PathVariable Long productId) {
        LOGGER.info("START - getInventoryWithProductDetails: {}", productId);
        InventoryItem item = getInventory(productId);
        Map productDetails = webClientBuilder.build()
                .get()
                .uri("http://product-service/products/" + productId)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        Map<String, Object> result = new java.util.HashMap<>();
        result.put("inventory", item);
        result.put("product", productDetails);
        LOGGER.info("END");
        return result;
    }
}
