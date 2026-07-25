package com.cognizant.edge.config;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.RandomLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

// LB PDF Exercise 2: Custom random load balancer for distributing traffic
// Default is round-robin; this switches to random selection
//
// Deliberately NOT annotated @Configuration: it's registered per-service via
// @LoadBalancerClients(defaultConfiguration = LoadBalancerConfig.class) on
// EdgeGatewayApplication instead, so Spring Cloud LoadBalancer instantiates it inside each
// named client's own child context (where the "name" property this bean reads is actually
// set), rather than the shared root application context.
public class LoadBalancerConfig {

    @Bean
    public ReactorLoadBalancer<ServiceInstance> randomLoadBalancer(
            Environment environment,
            LoadBalancerClientFactory loadBalancerClientFactory) {
        String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
        return new RandomLoadBalancer(
                loadBalancerClientFactory.getLazyProvider(name, ServiceInstanceListSupplier.class),
                name);
    }
}
