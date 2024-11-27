package com.tdav.services.sandbox.services;

import org.springframework.stereotype.Service;

import com.tdav.services.sandbox.entities.convenience.AuthResponse;
import com.tdav.services.sandbox.entities.convenience.GitHubUser;
import com.tdav.services.sandbox.entities.convenience.OAuthRequest;

@Service
public class LoginOrchestratorService {

  private final GitHubOAuthService gitHubOAuthService;
  private final UserService userService;

  public LoginOrchestratorService(GitHubOAuthService gitHubOAuthService, UserService userService) {
    this.gitHubOAuthService = gitHubOAuthService;
    this.userService = userService;
  }

  public AuthResponse handleGitHubLogin(OAuthRequest oAuthRequest) {
    String authCode = oAuthRequest.getCode();
    String accessToken = gitHubOAuthService.exchangeCodeForAccessToken(authCode);
    GitHubUser gitHubUser = gitHubOAuthService.getUserInfo(accessToken);
    return userService.generateAuthResponseWithAdmin(gitHubUser);
  }

}
