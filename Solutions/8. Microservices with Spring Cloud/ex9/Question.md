# Exercise: Inventory Service (Inventory Management System with Service Discovery)

**Source:** "1. Microservices using Spring Boot 3 exercises.pdf" (Questions/Microservices) — Exercise 2, "Inventory Management System with Service Discovery"

## Problem

Create:
- Product Service: Manage products and stock (see `ex8`).
- **Inventory Service**: Track stock levels for each product.

## Requirements

- Use Spring Cloud Netflix Eureka for service discovery (register with `eureka-discovery-server`,
  see `ex1`).
- Implement centralized configuration using Spring Cloud Config Server (optional stretch goal —
  externalize configuration such as stock thresholds if a Config Server is introduced).

## This module (`inventory-service`)

Implement REST endpoints to track stock levels per product, exposing an `InventoryItem` model
and a controller under an appropriate base path (e.g. `/inventory`). Register the service with
Eureka using `spring.application.name=inventory-service` and the Eureka Discovery Client
dependency, coordinating with `product-service` to keep stock counts consistent.
