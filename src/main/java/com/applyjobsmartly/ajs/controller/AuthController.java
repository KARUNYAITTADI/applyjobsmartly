package com.applyjobsmartly.ajs.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.applyjobsmartly.ajs.dto.request.LoginRequest;
import com.applyjobsmartly.ajs.dto.request.RegisterRequest;
import com.applyjobsmartly.ajs.dto.request.VerifyOtpRequest;
import com.applyjobsmartly.ajs.dto.response.AuthResponse;
import com.applyjobsmartly.ajs.service.AuthService;
import com.applyjobsmartly.ajs.util.ResponseModel;
import com.applyjobsmartly.ajs.util.StatusCodeEnum;
import com.applyjobsmartly.ajs.util.StatusCodeMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ResponseModel<String>> register(
            @Valid @RequestBody RegisterRequest request) {

        log.info("begin AuthController --> register");

        authService.register(request.getEmail());

        log.info("end AuthController --> register");

        return StatusCodeMapper.build(
                StatusCodeEnum.CREATED,
                "OTP sent to email");
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<ResponseModel<String>> verifyOtp(
            @Valid @RequestBody VerifyOtpRequest request) {

        log.info("begin AuthController --> verifyOtp");

        authService.verifyOtp(request);

        log.info("end AuthController --> verifyOtp");

        return StatusCodeMapper.build(
                StatusCodeEnum.SUCCESS,
                "Email verified successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseModel<AuthResponse>> login(
            @Valid @RequestBody LoginRequest request) {

        log.info("begin AuthController --> login");

        AuthResponse response = authService.login(request.getEmail());

        log.info("end AuthController --> login");

        return StatusCodeMapper.build(StatusCodeEnum.SUCCESS, response);
    }
}
