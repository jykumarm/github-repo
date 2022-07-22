package com.jykumarm.githubrepos.controller.service;

import com.jykumarm.githubrepos.controller.dto.RepositoriesDto;
import com.jykumarm.githubrepos.controller.models.RepositoryQuery;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
public interface RepositoryService {
    RepositoriesDto getRepositories(@Valid RepositoryQuery queryObject);
}
