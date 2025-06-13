package com.williest.hazavao.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class MalagasyDefinitionService {
  @Value("${openai.api.key}")
  private final String apiKey;

  private static final String API_URL = "https://api.openai.com/v1/chat/completions";

  public String getDefinition(String teny) {
    RestTemplate restTemplate = new RestTemplate();

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setBearerAuth(apiKey);

    List<Map<String, String>> messages =
        List.of(
            Map.of("role", "system", "content", "Tu es un expert en langue malgache."),
            Map.of("role", "user", "content", "Définis le mot malgache : " + teny));

    Map<String, Object> requestBody = new HashMap<>();
    requestBody.put("model", "gpt-3.5-turbo");
    requestBody.put("messages", messages);

    HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

    ResponseEntity<Map> response = restTemplate.postForEntity(API_URL, requestEntity, Map.class);

    List<Map> choices = (List<Map>) response.getBody().get("choices");
    Map message = (Map) choices.get(0).get("message");

    return (String) message.get("content");
  }
}
