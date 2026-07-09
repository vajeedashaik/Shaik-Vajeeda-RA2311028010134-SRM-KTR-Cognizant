# Exercise: Centralized Authentication and SSO (OAuth 2.1/OIDC and JWT)

**Source:** "0. Sample Microservices exercises.pdf" (Questions/Microservices) — "Sample exercises on Centralized Authentication and SSO with Spring Boot 3 and Spring Cloud"

This document bundles three exercises; the `oauth-security` module implements the JWT-based
approach from Exercise 3 (with elements of Exercises 1 and 2 also reflected in its
`SecurityConfig`/`UserController`).

## Exercise 1: Implementing Centralized Authentication with OAuth 2.1/OIDC

**Task:** Implement centralized authentication using OAuth 2.1/OIDC.

1. Add `spring-boot-starter-security` and `spring-boot-starter-oauth2-client` to `pom.xml`.
2. Configure OAuth2 client registration/provider properties (client id/secret, scopes,
   authorization-grant-type, redirect-uri, provider endpoints) in `application.yml`.
3. Create a security configuration class enabling `oauth2Login()` and requiring authentication
   for all requests.
4. Implement a controller (`/user`) that returns the authenticated `Principal`.

## Exercise 2: Configuring Authorization Servers and Resource Servers

**Task:** Configure an Authorization Server and Resource Server.

1. Add `spring-boot-starter-security` and `spring-boot-starter-oauth2-resource-server`.
2. Configure `spring.security.oauth2.resourceserver.jwt.issuer-uri` in `application.yml`.
3. Create a security configuration class enabling `oauth2ResourceServer().jwt()`.
4. Implement a controller with a secured endpoint (`/secure`).

## Exercise 3: Using JSON Web Tokens (JWT) for Secure Communication

**Task:** Use JWTs for secure communication between services.

1. Add `spring-boot-starter-security` and a JWT library (e.g. `jjwt`) to `pom.xml`.
2. Configure a signing secret (`spring.security.jwt.secret`) in `application.yml`.
3. Implement:
   - A `JwtConfig` exposing the configured secret.
   - A `JwtTokenProvider` that creates and validates signed JWTs (1-hour validity) and extracts
     the `Authentication` from a token.
   - A `JwtTokenFilter` (`OncePerRequestFilter`) that reads the `Authorization: Bearer <token>`
     header, validates the token, and populates the `SecurityContextHolder`.
   - A `SecurityConfig` that registers the JWT filter before
     `UsernamePasswordAuthenticationFilter` and requires authentication for all requests.

## This module (`oauth-security`)

Implements the JWT flow described in Exercise 3: `JwtTokenProvider`, `JwtTokenFilter`, and
`SecurityConfig` under `com.cognizant.oauth`, plus a `UserController` exposing the authenticated
user (in the spirit of Exercise 1's `/user` endpoint).

**Note on matching confidence:** unlike the other 11 services, this is the one module the task
description flagged as potentially having no clean written assignment in this folder. On
inspection, "0. Sample Microservices exercises.pdf" turned out to be a solid, specific match —
its Exercise 3 code samples (`JwtConfig`, `JwtTokenProvider`, `JwtTokenFilter`, `SecurityConfig`)
line up closely with the actual classes found in `oauth-security/src`. So a real source document
was found for this module after all.
