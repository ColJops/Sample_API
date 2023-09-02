package com.gitrestapi.service;

import com.gitrestapi.repository.GitHubRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class GitHubService {

    private final RestTemplate restTemplate;
    private final String githubApiUrl;

    public GitHubService(RestTemplate restTemplate, @Value("${github.api.url}") String githubApiUrl) {
        this.restTemplate = restTemplate;
        this.githubApiUrl = githubApiUrl;
    }

    public List<GitHubRepository> getUserRepositories(String username) {
        String apiUrl = githubApiUrl + "/users/" + username + "/repos";
        ResponseEntity<GitHubRepository[]> response = restTemplate.exchange(apiUrl, HttpMethod.GET, null, GitHubRepository[].class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return Arrays.asList(Objects.requireNonNull(response.getBody()));
        }
        return Collections.emptyList();
    }

    // Other methods for getting branch information, handling errors, etc.
}

