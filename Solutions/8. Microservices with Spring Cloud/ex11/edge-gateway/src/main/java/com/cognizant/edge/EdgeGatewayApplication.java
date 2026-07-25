package com.cognizant.edge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;

import com.cognizant.edge.config.LoadBalancerConfig;

// LB PDF Exercises 1-3: Edge Gateway with routing, load balancing, circuit breaker
//
// @LoadBalancerClients registers LoadBalancerConfig as the default configuration for each
// per-service load-balancer child context Spring Cloud LoadBalancer creates. This is required
// (not optional) because LoadBalancerConfig is no longer a @Configuration bean auto-detected by
// component scanning — if it were, it would live in the root application context instead, where
// the per-client "name" property it needs is never set, and RandomLoadBalancer.choose() fails
// with a NullPointerException on every load-balanced request.
@SpringBootApplication
@EnableDiscoveryClient
@LoadBalancerClients(defaultConfiguration = LoadBalancerConfig.class)
public class EdgeGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(EdgeGatewayApplication.class, args);
    }
}
