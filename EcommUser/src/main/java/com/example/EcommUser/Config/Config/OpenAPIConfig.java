package com.example.EcommUser.Config.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI UserServiceAPI() {
        return new OpenAPI()
                .info(new Info().title("User Service.")
                        .description("This is a User Microservice of Ecommerce Application.")
                        .version("v0.0")
                        .license(new License().name("Apache 2.0")));
    }
}

