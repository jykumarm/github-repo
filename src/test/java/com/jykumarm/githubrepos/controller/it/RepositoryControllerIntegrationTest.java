package com.jykumarm.githubrepos.controller.it;

import com.jykumarm.githubrepos.GithubReposApplication;
import com.jykumarm.githubrepos.controller.dto.RepositoriesDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = GithubReposApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RepositoryControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testAllRepositories()
    {
        assertTrue(
                this.restTemplate
                        .getForObject("http://localhost:"+port+"/api/v1/repositories?date=2022-01-11", RepositoriesDto.class)
                        .getItems().size() != 0);
    }

    @Test
    public void testAllRepositoriesWithLanguageParam()
    {
        String language = "java";
        assertTrue(
                this.restTemplate
                        .getForObject("http://localhost:"+port+"/api/v1/repositories?date=2022-01-11&language="+language, RepositoriesDto.class)
                        .getItems().get(0).getLanguage().equalsIgnoreCase(language));
    }

    @Test
    public void testAllRepositoriesWithPageSizeParam()
    {
        int count = 2;
        assertTrue(
                this.restTemplate
                        .getForObject("http://localhost:"+port+"/api/v1/repositories?date=2022-01-11&pageSize=" + String.valueOf(count), RepositoriesDto.class)
                        .getItems().size() == 2);
    }

    @Test
    public void testAllRepositoriesWithInvalidPageSizeParam() {
        int invalidPageCount = -1;
        ResponseEntity<String> response = this.restTemplate
                .getForEntity("http://localhost:"+port+"/api/v1/repositories?date=2022-01-11&pageSize=" + String.valueOf(invalidPageCount), String.class);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testAllRepositoriesWithInvalidDateParam() {
        String dateString = "2022-01-11111";
        ResponseEntity<String> response = this.restTemplate
                .getForEntity("http://localhost:"+port+"/api/v1/repositories?date=" +dateString, String.class);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }
}
