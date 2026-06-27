package com.cognizant.edge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

// LB PDF Exercises 1-3: Edge Gateway with routing, load balancing, circuit breaker
@SpringBootApplication
@EnableDiscoveryClient
public class EdgeGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(EdgeGatewayApplication.class, args);
    }
}
