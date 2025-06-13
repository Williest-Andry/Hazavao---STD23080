package com.williest.hazavao.endpoint.rest.controller;

import com.williest.hazavao.service.MalagasyDefinitionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MalagasyDefinitionController {
  private final MalagasyDefinitionService malagasyDefinitionService;

  @GetMapping("/hazavao")
  public ResponseEntity<String> getMalagasyDefinition(@RequestParam String teny) {
    String definition = malagasyDefinitionService.getDefinition(teny);
    return ResponseEntity.ok(definition);
  }
}
