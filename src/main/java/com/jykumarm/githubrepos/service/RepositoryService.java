package com.jykumarm.githubrepos.service;

import com.jykumarm.githubrepos.dto.RepositoriesDto;
import com.jykumarm.githubrepos.models.RepositoryQuery;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
public interface RepositoryService {
    RepositoriesDto getRepositories(@Valid RepositoryQuery queryObject);
}
