package com.personal.finance_tracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.personal.finance_tracker.dto.ChatRequest;
import com.personal.finance_tracker.dto.ChatResponse;

import io.github.cdimascio.dotenv.Dotenv;

@RestController
public class ChatController {

  private RestTemplate restTemplate;
  private String model;
  private String apiUrl;

  @Autowired
  public ChatController(@Qualifier("openaiRestTemplate") RestTemplate restTemplate) {
    Dotenv dotenv = Dotenv.configure().load();
    this.restTemplate = restTemplate;
    this.model = dotenv.get("OPENAI_MODEL");
    this.apiUrl = dotenv.get("OPENAI_URL");
  }

  @GetMapping("/chat")
  public String chat(@RequestParam String prompt) {
    ChatRequest request = new ChatRequest(model, prompt);

    ChatResponse response = restTemplate.postForObject(apiUrl, request, ChatResponse.class);

    if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
      return "No response";
    }

    return response.getChoices().get(0).getMessage().getContent();
  }
}
