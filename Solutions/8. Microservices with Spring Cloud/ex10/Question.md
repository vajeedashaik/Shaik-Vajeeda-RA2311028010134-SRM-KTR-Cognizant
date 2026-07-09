# Exercise: Resilient Payment Service with Circuit Breaker

**Source:** "1. Microservices using Spring Boot 3 exercises.pdf" (Questions/Microservices) — Exercise 4, "Resilient Microservices with Circuit Breaker"

## Problem

A **Payment Service** calls a slow third-party API.

## Requirements

- Implement a Circuit Breaker and fallback logic using Resilience4j.
- Log and monitor fallback events.

## This module (`payment-service`)

Implement a `PaymentController` that processes payments by delegating to a
`ThirdPartyPaymentService` representing the (potentially slow/unreliable) external payment
provider. Wrap the call to the third-party service with a Resilience4j `@CircuitBreaker` (and
optionally `@TimeLimiter`/`@Retry`), providing a fallback method that returns a graceful
degraded response when the circuit is open or the call times out. Log every fallback invocation
so failures are observable.
