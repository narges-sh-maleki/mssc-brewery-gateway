package com.sfg.beerworks.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MsscBreweryGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsscBreweryGatewayApplication.class, args);
	}

}
