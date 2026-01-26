package com.applyjobsmartly.ajs.dto.request;

import lombok.Data;

@Data
public class JobFilterRequest {

    private String jobRole;
    private String location;
    private Integer minExperience;
    private Integer maxExperience;

    private Boolean verifiedOnly;
    private Boolean includeExternalJobs;

    // pagination
    private Integer pageSize = 10;
    private Integer pageNumber = 1;
}
