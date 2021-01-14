package com.sfg.beerworks.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

//@Configuration
//@Profile("google")
public class GoogleConfig {
    @Bean
    public RouteLocator googleRouteLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route(p -> p
                        .path("/googlesearch2")
                        .filters(f -> f.rewritePath("",""))
                        .uri("https://google.com")
                        )

                .build();
    }
}
