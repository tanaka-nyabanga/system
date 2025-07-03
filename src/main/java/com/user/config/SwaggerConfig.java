package com.user.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi roadWatchSwagger() {
        return GroupedOpenApi.builder()
                .group("Tanaka User Management System APIs")
                .packagesToScan("com.user")
                .build();
    }
}

