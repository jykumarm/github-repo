package com.jykumarm.githubrepos.service;

import com.jykumarm.githubrepos.models.GithubReposResponse;
import com.jykumarm.githubrepos.models.Repository;
import com.jykumarm.githubrepos.models.RepositoryQuery;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RepositoryServiceImplTest {

    @Mock
    private GithubDataProviderImpl githubDataProvider;

    @InjectMocks
    private RepositoryServiceImpl repositoryService;

    @Test
    void testGetRepositories() {

        String testLanguage = "JAVA";
        Date testDate = new Date();

        var mockGithubAPIResponse = new GithubReposResponse();
        mockGithubAPIResponse.setItems(Arrays.asList(new Repository[30]));
        Mockito.when(githubDataProvider.getRepositories(testLanguage, testDate)).thenReturn(mockGithubAPIResponse);

        var query1 = RepositoryQuery.builder().date(testDate).language(testLanguage).pageSize(2).build();
        var results1 = repositoryService.getRepositories(query1);

        assertNotNull(results1);
        assertNotNull(results1.getItems());
        assertTrue(results1.getItems().size() == 2);

        // test with different count
        var query2 = RepositoryQuery.builder().date(testDate).language(testLanguage).pageSize(40).build();
        var results2 = repositoryService.getRepositories(query2);

        assertNotNull(results2);
        assertNotNull(results2.getItems());
        //only 30 entries are provided
        assertTrue(results2.getItems().size() != 40);
        assertTrue(results2.getItems().size() == 30);

    }

}