package com.example.trip.global.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(servers = {@Server(url = "http://jlog.shop", description = "Default Server URL"),
        @Server(url = "http://localhost:8080/", description = "Default Local URL")})
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI(){
        Info info = new Info()
                .title("JLOG API")
                .description("API 설명서");

        return new OpenAPI()
                .components(new Components()).info(info);
    }
}
