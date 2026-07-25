package com.cognizant.springlearn.controller;

import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

// Handson 5 (file 5): JWT Authentication service
@RestController
public class AuthenticationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    // GET /authenticate - reads Basic Auth header, generates JWT
    @GetMapping("/authenticate")
    public Map<String, String> authenticate(@RequestHeader("Authorization") String authHeader) {
        LOGGER.info("START");
        LOGGER.debug("Authorization header: {}", authHeader);

        String user = getUser(authHeader);
        // Request already passed HTTP Basic auth (see SecurityConfig), so the caller's
        // granted roles are available here; embed them in the token as a "roles" claim
        // so JwtAuthorizationFilter can restore proper authorities instead of an empty list.
        Collection<? extends GrantedAuthority> authorities =
                SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        String token = generateJwt(user, authorities);

        Map<String, String> map = new HashMap<>();
        map.put("token", token);

        LOGGER.info("END");
        return map;
    }

    // Decode Base64 Basic Auth header to extract username
    private String getUser(String authHeader) {
        LOGGER.info("START");
        // authHeader format: "Basic <base64(user:password)>"
        String encodedCredentials = authHeader.substring("Basic ".length());
        LOGGER.debug("Encoded credentials: {}", encodedCredentials);

        byte[] decodedBytes = Base64.getDecoder().decode(encodedCredentials);
        String decodedCredentials = new String(decodedBytes);
        LOGGER.debug("Decoded credentials: {}", decodedCredentials);

        String user = decodedCredentials.substring(0, decodedCredentials.indexOf(":"));
        LOGGER.debug("User: {}", user);
        LOGGER.info("END");
        return user;
    }

    // Generate JWT token valid for 20 minutes, embedding the user's roles as a claim
    private String generateJwt(String user, Collection<? extends GrantedAuthority> authorities) {
        LOGGER.info("START");
        String roles = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        JwtBuilder builder = Jwts.builder();
        builder.setSubject(user);
        builder.claim("roles", roles);
        builder.setIssuedAt(new Date());
        // Expire in 20 minutes (1200000 ms)
        builder.setExpiration(new Date((new Date()).getTime() + 1200000));
        builder.signWith(SignatureAlgorithm.HS256, "secretkey");
        String token = builder.compact();
        LOGGER.debug("Generated token: {}", token);
        LOGGER.info("END");
        return token;
    }
}
