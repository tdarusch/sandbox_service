package com.tdav.services.sandbox.utils;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

public class RandomStringGenerator {
  private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"; 

  public static String generateRandomString(int length, String seed) {
    StringBuilder sb = new StringBuilder(length);
    SecureRandom random = new SecureRandom(seed.getBytes(StandardCharsets.UTF_8));

    for (int i = 0; i < length; i++) {
      int randomIndex = random.nextInt(CHARACTERS.length());
      sb.append(CHARACTERS.charAt(randomIndex));
    }

    return sb.toString();
  }
}
