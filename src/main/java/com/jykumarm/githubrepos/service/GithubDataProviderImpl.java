package com.jykumarm.githubrepos.service;

import com.jykumarm.githubrepos.models.GithubReposResponse;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;


@Service
@Slf4j
@RequiredArgsConstructor
public class GithubDataProviderImpl implements GithubDataProvider {

    private final RestTemplate restTemplate;
    private static final String GITHUB_API = "https://api.github.com/search/repositories?q=";
    private static final String SORT_BY_RATING_WITH_DEFAULT_COUNT = "sort=stars&order=desc&per_page=100";

    /**
     * method makes external github API request
     * with default count as 100 entries.
     *
     * @param language
     * @param date
     * @return
     */
    @Override
    @Cacheable(cacheNames = "repositories", keyGenerator = "keyGenerator")
    @Retry(name = "search Repos form github open API", fallbackMethod = "returnEmptyList")
    public GithubReposResponse getRepositories(String language, Date date) {
        var response = restTemplate.getForEntity(getEndPoint(language, date), GithubReposResponse.class);
        log.debug("External Github API call is invoked");
        return response.getBody();
    }

    /**
     * In case of Github API failiures, this fallback method will get executed.
     *
     * @param language
     * @param date
     * @param e
     * @return
     */
    private GithubReposResponse returnEmptyList(String language, Date date, Exception e) {
        log.error("Github repository search Api call failed with exception: {}, returning empty list", e.getMessage());
        var response = new GithubReposResponse();
        response.setItems(Collections.emptyList());
        return response;
    }


    /**
     * Constructs Git hub API end points
     *
     * @param language
     * @param date
     * @return
     */
    private String getEndPoint(String language, Date date) {
        var apiBuilder = new StringBuilder(GITHUB_API);

        if (!ObjectUtils.isEmpty(language)) {
            apiBuilder.append("language:").append(language).append("+");
        }
        if (!ObjectUtils.isEmpty(date)) {
            apiBuilder.append("created:>").append(new SimpleDateFormat("yyyy-MM-dd").format(date)).append("&");
        }
        apiBuilder.append(SORT_BY_RATING_WITH_DEFAULT_COUNT);
        log.debug("Github repo search api constructed: {}", apiBuilder);
        return apiBuilder.toString();
    }

}
