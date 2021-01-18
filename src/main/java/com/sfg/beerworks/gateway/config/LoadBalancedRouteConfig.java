package com.sfg.beerworks.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@Profile("local-discovery")
//@Configuration
public class LoadBalancedRouteConfig {
    @Bean
    public RouteLocator loadBalancedRouteLocator(RouteLocatorBuilder builder){

        return builder.routes()
                .route("beer-service",r -> r
                        .path("/api/v1/beer/*","/api/v1/beer/beerUpc/*","/api/v1/beer*")
                        .uri("lb://beer-service"))
                .route("beer-order-service", spec -> spec
                        .path("/api/v1/customers**","/api/v1/customers/**")
                        .uri("lb://beer-order-service"))
                .route("beer-inventory-service", spec -> spec
                        .path("/api/v1/beer/*/inventory")
                        .uri("lb://beer-inventory-service"))
                .build();
    }
}
