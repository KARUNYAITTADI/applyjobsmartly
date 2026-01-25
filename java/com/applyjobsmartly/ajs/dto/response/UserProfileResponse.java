package com.applyjobsmartly.ajs.dto.response;

import lombok.Data;

@Data
public class UserProfileResponse {

    private String jobRole;
    private Integer experienceYears;
    private String preferredLocation;
    private String skills;
}
