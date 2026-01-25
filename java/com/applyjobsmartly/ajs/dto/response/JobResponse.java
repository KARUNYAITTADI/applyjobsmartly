package com.applyjobsmartly.ajs.dto.response;

import lombok.Data;

@Data
public class JobResponse {

    private String title;
    private String companyName;
    private String location;
    private String experience;
    private String jobType;
    private boolean verified;
    private String applyUrl;
    private String source;
}
