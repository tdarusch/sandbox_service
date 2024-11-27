package com.tdav.services.sandbox.services;

import org.springframework.stereotype.Service;

import com.tdav.services.sandbox.entities.User;
import com.tdav.services.sandbox.entities.convenience.AuthResponse;
import com.tdav.services.sandbox.entities.convenience.GitHubUser;
import com.tdav.services.sandbox.repositories.UserRepository;

@Service
public class UserService {

  private final UserRepository userRepo;
  private final JwtTokenProvider jwtTokenProvider;

  public UserService(UserRepository userRepo, JwtTokenProvider jwtTokenProvider) {
    this.userRepo = userRepo;
    this.jwtTokenProvider = jwtTokenProvider;
  }

  public AuthResponse generateAuthResponseWithAdmin(GitHubUser gitHubUser) {
    User user = findOrCreateUser(gitHubUser);
    boolean isAdmin = user.getAdmin();
    String jwtToken = jwtTokenProvider.generateToken(user, isAdmin);
    return new AuthResponse(jwtToken, isAdmin);
  }

  public User findOrCreateUser(GitHubUser gitHubUser) {
    return userRepo.findByGitHubId(gitHubUser.getId())
      .orElseGet(() -> createUserFromGitHub(gitHubUser));
  }

  private User createUserFromGitHub(GitHubUser gitHubUser) {
    User user = new User();
    user.setGitHubId(gitHubUser.getId());
    user.setUsername(gitHubUser.getLogin());
    user.setName(gitHubUser.getName());
    user.setEmail(gitHubUser.getEmail());
    user.setAvatarUrl(gitHubUser.getAvatarUrl());
    user.setAdmin(false);
    return userRepo.save(user);
  }

}
