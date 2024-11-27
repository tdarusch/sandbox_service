package com.tdav.services.sandbox.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tdav.services.sandbox.entities.convenience.AuthResponse;
import com.tdav.services.sandbox.entities.convenience.OAuthRequest;
import com.tdav.services.sandbox.services.LoginOrchestratorService;


@RestController
@RequestMapping("/auth")
public class AuthController {
  
  private final LoginOrchestratorService loginOrchestratorService;

  public AuthController(LoginOrchestratorService loginOrchestratorService) {
    this.loginOrchestratorService = loginOrchestratorService;
  }

  @PostMapping("/github")
  public AuthResponse handleGitHubLogin(@RequestBody OAuthRequest oAuthRequest) {
    return loginOrchestratorService.handleGitHubLogin(oAuthRequest);
  }

}
