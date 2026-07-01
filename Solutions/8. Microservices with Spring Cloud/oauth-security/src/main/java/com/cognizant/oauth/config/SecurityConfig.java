package com.cognizant.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

// Exercise 1: OAuth2 Login — users authenticate via OIDC provider (e.g., Google)
// Exercise 2: Resource Server — secure endpoints, validate JWT from issuer
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Exercise 1 config: enable OAuth2 login (OIDC)
    @Bean
    public SecurityFilterChain oauth2LoginFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/public/**").permitAll()
                .anyRequest().authenticated()
            )
            .oauth2Login(oauth2 -> oauth2
                .defaultSuccessUrl("/user", true)
            )
            // Exercise 2: also accept JWT Bearer tokens from resource server perspective
            .oauth2ResourceServer(rs -> rs.jwt(jwt -> {}));
        return http.build();
    }
}
