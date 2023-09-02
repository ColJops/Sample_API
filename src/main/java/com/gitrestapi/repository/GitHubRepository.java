package com.gitrestapi.repository;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

public class GitHubRepository {
    @Getter
    @Setter

    private String name;
    private String owner;
    private List<BranchInfo> branches;

}

