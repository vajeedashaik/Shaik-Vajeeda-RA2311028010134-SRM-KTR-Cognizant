package com.cognizant.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

// SB3 Exercise 1: User Service — manages users, calls order-service via WebClient
@SpringBootApplication
@EnableDiscoveryClient
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

    // Load-balanced WebClient bean for calling order-service
    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}
