package com.jykumarm.githubrepos.controller.dto;

import com.jykumarm.githubrepos.controller.models.Repository;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class RepositoriesDto implements Serializable {
    private long count;
    private List<Repository> items;
}
