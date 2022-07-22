package com.jykumarm.githubrepos.controller;

import com.jykumarm.githubrepos.controller.dto.RepositoriesDto;
import com.jykumarm.githubrepos.controller.models.RepositoryQuery;
import com.jykumarm.githubrepos.controller.service.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@RestController
@RequestMapping("/api/v1/repositories")
public class RepositoryController {

    @Autowired
    private RepositoryService repositoryService;

    @GetMapping()
    private RepositoriesDto getRepositories(@RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize,
                                            @RequestParam(name = "language", required = false) String language,
                                            @RequestParam(name = "date")
                                            @DateTimeFormat(pattern = "yyyy-MM-dd") Date date
    ) {

        return repositoryService.getRepositories(RepositoryQuery.builder()
                .pageSize(pageSize)
                .language(language)
                .date(date)
                .build());

    }
}
