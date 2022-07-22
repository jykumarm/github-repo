package com.jykumarm.githubrepos.controller.service;

import com.jykumarm.githubrepos.controller.models.GithubReposResponse;

import java.util.Date;

public interface GithubDataProvider {
    GithubReposResponse getRepositories(String language, Date date);
}
