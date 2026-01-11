package com.applyjobsmartly.ajs.service;

import com.applyjobsmartly.ajs.dto.request.VerifyOtpRequest;
import com.applyjobsmartly.ajs.dto.response.AuthResponse;

public interface AuthService {

    void register(String email);

    void verifyOtp(VerifyOtpRequest request);

    AuthResponse login(String email);
}
