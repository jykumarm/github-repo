package com.jykumarm.githubrepos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class GithubReposApplication {

    public static void main(String[] args) {
        SpringApplication.run(GithubReposApplication.class, args);
    }

}
