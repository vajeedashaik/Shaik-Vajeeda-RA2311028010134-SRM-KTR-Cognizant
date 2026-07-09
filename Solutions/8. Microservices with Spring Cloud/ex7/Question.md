# Exercise: Order Service (User and Order Management System)

**Source:** "1. Microservices using Spring Boot 3 exercises.pdf" (Questions/Microservices) — Exercise 1, "Build a User and Order Management System"

## Problem

Create two microservices:
- User Service to manage users (see `ex6`).
- **Order Service** to manage orders placed by users.

## Requirements

- Use REST APIs.
- Communicate between services using `WebClient` (Spring WebFlux) or `OpenFeign` — this
  service calls `user-service` to fetch/validate user information associated with an order.
- Store data in MySQL or PostgreSQL (an in-memory/mock data store is acceptable for a
  classroom exercise).

## This module (`order-service`)

Implement REST endpoints to manage orders (create, retrieve, list, etc.), exposing an `Order`
model and a controller under an appropriate base path (e.g. `/orders`). When creating or
displaying an order, call out to `user-service` to resolve the associated user's details.
