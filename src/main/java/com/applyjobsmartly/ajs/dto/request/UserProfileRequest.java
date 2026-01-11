package com.applyjobsmartly.ajs.dto.request;

import lombok.Data;

@Data
public class UserProfileRequest {

    private String jobRole;
    private Integer experienceYears;
    private String preferredLocation;
    private String skills;
}
