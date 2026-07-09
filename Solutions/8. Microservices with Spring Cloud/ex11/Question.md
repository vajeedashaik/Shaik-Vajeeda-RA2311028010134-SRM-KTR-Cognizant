# Exercise: Edge Services — Routing, Load Balancing and Resilience

**Source:** "0. Sample Microservices Load balancing exercises.pdf" (Questions/Microservices) — "Sample Hands-on Exercises on Edge Services and API Gateway with Spring Boot 3 and Spring Cloud"

This document bundles three exercises; the `edge-gateway` module implements all three as one
edge service.

## Exercise 1: Implementing Edge Services for Routing and Filtering

**Task:** Implement an edge service for routing and filtering requests in a microservices
architecture using Spring Boot 3 and Spring Cloud.

1. Create a Spring Boot project with the `spring-cloud-starter-gateway` dependency.
2. Configure routing in `application.properties`, e.g.:
   ```properties
   spring.cloud.gateway.routes[0].id=example_route
   spring.cloud.gateway.routes[0].uri=http://example.org
   spring.cloud.gateway.routes[0].predicates[0]=Path=/example/**
   ```
3. Implement a custom `GlobalFilter` that logs each incoming request's URI.
4. Test the routing and filtering functionality.

## Exercise 2: Load Balancing in an API Gateway

**Task:** Implement load balancing in the gateway using Spring Boot 3 and Spring Cloud.

1. Add `spring-cloud-starter-loadbalancer` alongside the gateway starter.
2. Configure a load-balanced route using the `lb://` scheme, e.g.
   `spring.cloud.gateway.routes[0].uri=lb://example-service`.
3. Implement a custom `ReactorLoadBalancer<ServiceInstance>` bean (e.g. a random-selection
   load balancer) via a `LoadBalancerConfiguration` class.
4. Test that requests are distributed across instances of the target service.

## Exercise 3: Resilience Patterns in an API Gateway

**Task:** Implement resilience patterns in the gateway using Spring Boot 3 and Spring Cloud.

1. Add the `resilience4j-spring-boot2` dependency alongside the gateway starter.
2. Configure circuit breaker properties, e.g.:
   ```properties
   resilience4j.circuitbreaker.instances.exampleCircuitBreaker.registerHealthIndicator=true
   resilience4j.circuitbreaker.instances.exampleCircuitBreaker.slidingWindowSize=10
   resilience4j.circuitbreaker.instances.exampleCircuitBreaker.failureRateThreshold=50
   ```
3. Provide a `Customizer<ReactiveResilience4JCircuitBreakerFactory>` bean configuring default
   circuit breaker and time limiter settings.
4. Test the resilience/fallback behavior when a downstream call fails or is slow.

## This module (`edge-gateway`)

Combines all three: a `LoggingFilter` (Exercise 1), a `LoadBalancerConfig` (Exercise 2), and a
`ResilienceConfig` (Exercise 3), implemented as a single edge gateway service.
