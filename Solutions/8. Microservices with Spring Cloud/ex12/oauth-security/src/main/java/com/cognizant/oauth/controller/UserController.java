package com.cognizant.oauth.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// Exercise 1: Displays authenticated user info after OAuth2 login
// Exercise 2: Secured endpoint — only accessible with valid JWT
@RestController
public class UserController {

    // Exercise 1: GET /user — returns authenticated user principal from OAuth2 login
    @GetMapping("/user")
    public Principal user(Principal principal) {
        return principal;
    }

    // Exercise 2: GET /secure — requires valid JWT Bearer token
    @GetMapping("/secure")
    public String secure() {
        return "This is a secure endpoint - accessible only with valid JWT";
    }

    // Public endpoint — no authentication required
    @GetMapping("/public/hello")
    public String publicHello() {
        return "Public endpoint - no auth required";
    }
}
