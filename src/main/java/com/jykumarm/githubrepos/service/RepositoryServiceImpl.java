package com.jykumarm.githubrepos.service;

import com.jykumarm.githubrepos.dto.RepositoriesDto;
import com.jykumarm.githubrepos.models.RepositoryQuery;
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
