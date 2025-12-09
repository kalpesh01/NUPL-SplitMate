package com.splitmate.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("SplitMate API Documentation")
                                .version("1.0.0")
                                .description("API documentation for SplitMate Bill-Splitting Application")
                                .contact(new Contact()
                                        .name("Splitmate")
                                        .email("splitmate@support.com")
                                )
                );
    }
}
