package com.personal.finance_tracker.config;

import org.springframework.context.annotation.Bean;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;

@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .info(new Info()
            .title("Finance Tracker API")
            .version("1.0.0")
            .description("Documentation for the Finance Tracker API proyect with Spring Boot and OpenAPI"));
  }
}
