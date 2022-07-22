package com.jykumarm.githubrepos.service;

import com.jykumarm.githubrepos.models.GithubReposResponse;

import java.util.Date;

public interface GithubDataProvider {
    GithubReposResponse getRepositories(String language, Date date);
}
