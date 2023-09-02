package com.gitrestapi.controller;

import com.gitrestapi.repository.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.gitrestapi.repository.GitHubRepository;
import com.gitrestapi.service.GitHubService;

import java.util.Collections;
import java.util.List;

@RestController
public class GitHubController {

    private final GitHubService gitHubService;

    public GitHubController(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    @GetMapping(value = "/repositories", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GitHubRepository>> getRepositoriesJson(
            @RequestParam String username,
            @RequestHeader(value = "Accept", defaultValue = "application/json") String acceptHeader) {
        if (!"application/json".equals(acceptHeader)) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body(Collections.<GitHubRepository>singletonList(new ErrorMessage("406", "Unsupported Media Type")));
        }

        List<GitHubRepository> repositories = gitHubService.getUserRepositories(username);

        if (repositories.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonList(new ErrorMessage("404", "User not found")));
        }

        ResponseEntity<List<GitHubRepository>> ok = ResponseEntity.ok(repositories);
        return ok;
    }

    @GetMapping(value = "/repositories", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getRepositoriesXml(@RequestParam String username) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                .body(Collections.singletonList(new ErrorMessage("406", "Unsupported Media Type")));
    }
}

