package com.tdav.services.sandbox.services;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.tdav.services.sandbox.entities.convenience.GitHubUser;

@Service
public class GitHubOAuthService {
    private static final Logger logger = LoggerFactory.getLogger(GitHubOAuthService.class.getCanonicalName());

    private static final String TOKEN_URL = "https://github.com/login/oauth/access_token";
    private static final String USER_INFO_URL = "https://api.github.com/user";

    public String exchangeCodeForAccessToken(String code) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> body = new HashMap<>();
        body.put("client_id", "Ov23liTjhr8WXNdbACI3");
        body.put("client_secret", "d6770ceadb16d4368d66d6a6dc248da13f15dbcb");
        body.put("code", code);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate.exchange(TOKEN_URL, HttpMethod.POST, request, String.class);

        String responseBody = response.getBody();
        return parseAccessToken(responseBody);
    }

    public GitHubUser getUserInfo(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<Void> request = new HttpEntity<>(headers);
        try {
          ResponseEntity<GitHubUser> response = restTemplate.exchange(USER_INFO_URL, HttpMethod.GET, request, GitHubUser.class);
          return response.getBody();
        } catch (HttpClientErrorException e) {
          logger.error("Error fetching user info: ", e.getMessage());
          logger.error("Response Body: ", e.getResponseBodyAsString());
          throw e;
        }
    }

    private String parseAccessToken(String responseBody) {
        String[] params = responseBody.split("&");
        for (String param : params) {
            if (param.startsWith("access_token=")) {
                return param.split("=")[1];
            }
        }
        throw new RuntimeException("Access token not found in response");
    }
}
