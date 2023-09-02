package com.gitrestapi.repository;

import lombok.Getter;
import lombok.Setter;

public class BranchInfo {
    @Getter
    @Setter
    private String name;
    private String lastCommitSha;

    // Getters and setters
}
