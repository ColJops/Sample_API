package com.gitrestapi.repository;

import lombok.Getter;
import lombok.Setter;

public class ErrorMessage extends GitHubRepository {
    @Getter
    @Setter
    private String status;
    private String message;

    public ErrorMessage(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
