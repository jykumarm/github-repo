package com.jykumarm.githubrepos.controller.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;


@Data
public class Repository {
    private String name;
    @JsonProperty("full_name")
    private String fullName;
    private String url;
    private String language;
    @JsonProperty("created_at")
    private Date createdDate;
}
