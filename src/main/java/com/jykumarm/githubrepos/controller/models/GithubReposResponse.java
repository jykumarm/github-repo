package com.jykumarm.githubrepos.controller.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class GithubReposResponse {

    @JsonProperty("total_count")
    private long totalCount;

    @JsonProperty("incomplete_results")
    private boolean incompleteResults;

    private List<Repository> items;

}
