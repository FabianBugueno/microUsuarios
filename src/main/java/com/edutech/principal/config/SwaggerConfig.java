package com.edutech.principal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI opeanAPI(){
        return new OpenAPI()
            .info(new io.swagger.v3.oas.models.info.Info()
                .title("EduTech Usuarios")
                .version("1.0.0")
                .description("Micro servicios de Usuarios"));
    }
}   
