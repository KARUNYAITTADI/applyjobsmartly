package com.applyjobsmartly.ajs.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.applyjobsmartly.ajs.dto.request.UserProfileRequest;
import com.applyjobsmartly.ajs.dto.response.UserProfileResponse;
import com.applyjobsmartly.ajs.service.ProfileService;
import com.applyjobsmartly.ajs.util.ResponseModel;
import com.applyjobsmartly.ajs.util.StatusCodeEnum;
import com.applyjobsmartly.ajs.util.StatusCodeMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
@Slf4j
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping("/update")
    public ResponseEntity<ResponseModel<UserProfileResponse>> updateProfile(
            @RequestBody UserProfileRequest request) {

        log.info("begin ProfileController --> updateProfile");

        String email = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        UserProfileResponse response = profileService.updateProfile(email, request);

        log.info("end ProfileController --> updateProfile");

        return StatusCodeMapper.build(StatusCodeEnum.UPDATED, response);
    }
}
