package com.example.EcommProduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EcommProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommProductApplication.class, args);
	}

}
