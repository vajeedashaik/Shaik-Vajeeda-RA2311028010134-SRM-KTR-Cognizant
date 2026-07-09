# Exercise: User Service (User and Order Management System)

**Source:** "1. Microservices using Spring Boot 3 exercises.pdf" (Questions/Microservices) — Exercise 1, "Build a User and Order Management System"

## Problem

Create two microservices:
- **User Service** to manage users.
- **Order Service** to manage orders placed by users (see `ex7`).

## Requirements

- Use REST APIs.
- Communicate between services using `WebClient` (Spring WebFlux) or `OpenFeign` — the Order
  Service looks up user details from the User Service when creating/viewing an order.
- Store data in MySQL or PostgreSQL (an in-memory/mock data store is acceptable for a
  classroom exercise).

## This module (`user-service`)

Implement REST endpoints to manage users (create, retrieve, list, etc.), exposing a `User`
model and a controller under an appropriate base path (e.g. `/users`). This service acts as the
upstream dependency that `order-service` calls to validate/enrich order data with user
information.
