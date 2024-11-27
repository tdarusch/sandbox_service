package com.tdav.services.sandbox.services;

import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.tdav.services.sandbox.entities.User;

import io.jsonwebtoken.Jwts;

@Component
public class JwtTokenProvider {
  private final Key secretKey;
  private final long validityInMilliseconds = 3600000;

  public JwtTokenProvider(Key securityKey) {
    this.secretKey = securityKey;
  }

  public String generateToken(User user, boolean isAdmin) {
    Map<String, Object> claims = new HashMap<>();
    List<String> roles = new ArrayList<String>();
    if (isAdmin) {
      roles.add("ROLE_ADMIN");
    }
    roles.add("ROLE_USER");
    claims.put("roles", roles);

    return Jwts.builder()
        .setClaims(claims)
        .setSubject(user.getGitHubId())
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + validityInMilliseconds))
        .signWith(secretKey)
        .compact();
  }

}