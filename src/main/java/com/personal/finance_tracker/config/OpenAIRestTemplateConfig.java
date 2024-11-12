package com.personal.finance_tracker.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import io.github.cdimascio.dotenv.Dotenv;

@Configuration
public class OpenAIRestTemplateConfig {

  private String openaiApiKey;

  public OpenAIRestTemplateConfig(Environment environment) {
    Dotenv dotenv = Dotenv.configure().load();
    this.openaiApiKey = dotenv.get("OPENAI_API_KEY");
  }

  @Bean
  @Qualifier("openaiRestTemplate")
  public RestTemplate openaiRestTemplate() {
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.getInterceptors().add((request, body, execution) -> {
      request.getHeaders().add("Authorization", "Bearer " + openaiApiKey);
      return execution.execute(request, body);
    });
    return restTemplate;
  }
}
