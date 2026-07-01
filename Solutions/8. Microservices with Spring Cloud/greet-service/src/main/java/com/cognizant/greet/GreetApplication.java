package com.cognizant.greet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

// API Gateway handson: greet-service registered with Eureka, accessed through API gateway
@SpringBootApplication
@EnableDiscoveryClient
public class GreetApplication {
    public static void main(String[] args) {
        SpringApplication.run(GreetApplication.class, args);
    }
}
