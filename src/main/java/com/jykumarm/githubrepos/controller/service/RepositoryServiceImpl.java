package com.jykumarm.githubrepos.controller.service;

import com.jykumarm.githubrepos.controller.dto.RepositoriesDto;
import com.jykumarm.githubrepos.controller.models.RepositoryQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RepositoryServiceImpl implements RepositoryService {

    private final GithubDataProvider githubDataProvider;

    /**
     * Return Repositories data.
     *
     * @param queryObject
     * @return
     */
    @Override
    public RepositoriesDto getRepositories(RepositoryQuery queryObject) {
        var response = githubDataProvider.getRepositories(queryObject.getLanguage(), queryObject.getDate());
        var items = (response.getItems().size() > queryObject.getPageSize()) ?
                response.getItems().subList(0, queryObject.getPageSize()) : response.getItems();
        return new RepositoriesDto(response.getTotalCount(), items);
    }

}
