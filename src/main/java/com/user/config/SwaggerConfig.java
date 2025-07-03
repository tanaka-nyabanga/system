package com.user.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "User Manager Turtorial")
)
@Configuration
public class SwaggerConfig {

//    @Bean
//    public GroupedOpenApi userTutorial() {
//        return GroupedOpenApi.builder().
//                group("User Tutorial")
//                .packagesToScan("com.user")
//                .build();
//    }

}
