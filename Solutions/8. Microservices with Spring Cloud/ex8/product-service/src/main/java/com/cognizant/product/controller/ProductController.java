package com.cognizant.product.controller;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.product.model.Product;

// SB3 Exercise 2: Product REST API — manage products and stock
@RestController
@RequestMapping("/products")
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    private static final List<Product> PRODUCTS = Arrays.asList(
        new Product(1L, "Laptop", "Electronics", 1200.00, 50),
        new Product(2L, "Phone", "Electronics", 800.00, 100),
        new Product(3L, "Desk", "Furniture", 300.00, 20)
    );

    @GetMapping
    public List<Product> getAllProducts() {
        LOGGER.info("START");
        LOGGER.info("END");
        return PRODUCTS;
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        LOGGER.info("START - getProduct: {}", id);
        Product product = PRODUCTS.stream().filter(p -> p.getId().equals(id)).findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found: " + id));
        LOGGER.info("END");
        return product;
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        LOGGER.info("START - createProduct: {}", product.getName());
        LOGGER.info("END");
        return product;
    }
}
