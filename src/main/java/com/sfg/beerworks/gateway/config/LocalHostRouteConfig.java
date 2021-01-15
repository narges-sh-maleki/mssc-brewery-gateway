package com.sfg.beerworks.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class LocalHostRouteConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder){
       return  builder.routes()
                .route("beer-service",r -> r
                        .path("/api/v1/beer/*","/api/v1/beer/beerUpc/*","/api/v1/beer*")
                        .uri("http://localhost:8080"))
                .route("beer-order-service", spec -> spec
                        .path("/api/v1/customers**","/api/v1/customers/**")
                        .uri("http://localhost:8081"))
               .route("beer-inventory-service", spec -> spec
                       .path("/api/v1/beer/*/inventory")
                       .uri("http://localhost:8082"))
                .build();
    }
}
