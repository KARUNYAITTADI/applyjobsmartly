package com.applyjobsmartly.ajs.service;

import com.applyjobsmartly.ajs.dto.request.UserProfileRequest;
import com.applyjobsmartly.ajs.dto.response.UserProfileResponse;

public interface ProfileService {
    UserProfileResponse updateProfile(String email, UserProfileRequest request);
}
