package com.jykumarm.githubrepos.models;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Min;
import java.util.Date;

@Data
@Builder
@ToString
public class RepositoryQuery {
    private Date date;
    @Min(message="Page size value should be greater than 0", value=0)
    private int pageSize;
    private String language;
}
