package com.cognizant.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

// API Gateway handson: global filter — logs every incoming request URI
// Implements GlobalFilter so it runs for ALL routes (not just one)
@Component
public class LogFilter implements GlobalFilter, Ordered {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        LOGGER.info("Request: {}", exchange.getRequest().getURI());
        return chain.filter(exchange).then(Mono.fromRunnable(() ->
            LOGGER.info("Response status: {}", exchange.getResponse().getStatusCode())
        ));
    }

    @Override
    public int getOrder() {
        // Highest precedence — runs before all other filters
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
