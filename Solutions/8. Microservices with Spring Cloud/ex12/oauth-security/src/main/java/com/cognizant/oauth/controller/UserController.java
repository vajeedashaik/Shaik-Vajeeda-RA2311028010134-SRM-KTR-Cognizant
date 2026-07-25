package com.cognizant.oauth.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.oauth.security.JwtTokenProvider;

// Exercise 1: Displays authenticated user info after OAuth2 login
// Exercise 2: Secured endpoint — only accessible with valid JWT
@RestController
public class UserController {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    // Exercise 3: POST /login — issues a signed JWT for the given username so the
    // custom JwtTokenFilter/Provider flow can actually be exercised end-to-end
    // (e.g. curl -X POST "http://localhost:8088/login?username=alice")
    @PostMapping("/login")
    public Map<String, String> login(@RequestParam String username) {
        String token = jwtTokenProvider.createToken(username);
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return response;
    }

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
