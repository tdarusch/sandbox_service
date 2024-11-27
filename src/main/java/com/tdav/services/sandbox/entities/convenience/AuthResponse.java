package com.tdav.services.sandbox.entities.convenience;

public class AuthResponse {
  private String token;
  private boolean isAdmin;

  public AuthResponse(String token, boolean isAdmin) {
    this.token = token;
    this.isAdmin = isAdmin;
  }

  public String getToken() {
    return token;
  }

  public boolean isAdmin() {
    return isAdmin;
  }
}