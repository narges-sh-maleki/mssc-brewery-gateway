package com.sfg.beerworks.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("local-discovery")
@Configuration
public class LoadBalancedRouteConfig {
    @Bean
    public RouteLocator loadBalancedRouteLocator(RouteLocatorBuilder builder){

        return builder.routes()
                .route("beer-service",predicate -> predicate
                        .path("/api/v1/beer/*","/api/v1/beer/beerUpc/*","/api/v1/beer*")
                        .uri("lb://beer-service"))
                .route("beer-order-service", predicate -> predicate
                        .path("/api/v1/customers**","/api/v1/customers/**")
                        .uri("lb://beer-order-service"))
                .route("beer-inventory-service", predicate -> predicate
                        .path("/api/v1/beer/*/inventory")
                        .filters(filterSpec -> filterSpec
                            .circuitBreaker(config -> config
                                .setName("inventoryCB")
                                .setFallbackUri("forward:/inventory-failover")
                                .setRouteId("inv-failover")))
                        .uri("lb://beer-inventory-service"))
                .route("inventory-failover",predicate -> predicate
                        .path("/inventory-failover")
                        .uri("lb://inventory-failover")
                )
                .build();
    }
}
