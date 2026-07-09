# Exercise: Product Service (Inventory Management System with Service Discovery)

**Source:** "1. Microservices using Spring Boot 3 exercises.pdf" (Questions/Microservices) — Exercise 2, "Inventory Management System with Service Discovery"

## Problem

Create:
- **Product Service**: Manage products and stock.
- Inventory Service: Track stock levels for each product (see `ex9`).

## Requirements

- Use Spring Cloud Netflix Eureka for service discovery (register with `eureka-discovery-server`,
  see `ex1`).
- Implement centralized configuration using Spring Cloud Config Server (optional stretch goal —
  externalize configuration such as stock thresholds if a Config Server is introduced).

## This module (`product-service`)

Implement REST endpoints to manage products (create, retrieve, list, update stock, etc.),
exposing a `Product` model and a controller under an appropriate base path (e.g. `/products`).
Register the service with Eureka using `spring.application.name=product-service` and the Eureka
Discovery Client dependency.
