package com.applyjobsmartly.ajs.serviceImpl;

import java.time.Instant;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.applyjobsmartly.ajs.dto.request.VerifyOtpRequest;
import com.applyjobsmartly.ajs.dto.response.AuthResponse;
import com.applyjobsmartly.ajs.entity.User;
import com.applyjobsmartly.ajs.exception.CustomException;
import com.applyjobsmartly.ajs.exception.ResourceNotFoundException;
import com.applyjobsmartly.ajs.repository.UserRepository;
import com.applyjobsmartly.ajs.security.JwtUtil;
import com.applyjobsmartly.ajs.service.AuthService;
import com.applyjobsmartly.ajs.service.EmailService;
import com.applyjobsmartly.ajs.service.EmailService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final EmailService emailService;


    @Override
    public void register(String email) {

        log.info("begin AuthServiceImpl --> register");

        try {
            User user = userRepository.findByEmail(email)
                    .orElse(new User());

            String otp = generateOtp();

            user.setEmail(email);
            user.setOtp(otp);
            user.setOtpExpiry(Instant.now().plusSeconds(300)); // 5 mins
            user.setEmailVerified(false);
            user.setActive(true);

            userRepository.save(user);
         // after saving user
            emailService.sendOtpEmail(email, otp);

            // TEMP: log OTP (later integrate email service)
            log.info("OTP for {} is {}", email, otp);

        } catch (Exception e) {
            log.error("Registration failed", e);
            throw new CustomException("Unable to register");
        } finally {
            log.info("end AuthServiceImpl --> register");
        }
    }

    @Override
    public void verifyOtp(VerifyOtpRequest request) {

        log.info("begin AuthServiceImpl --> verifyOtp");

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        if (!user.getOtp().equals(request.getOtp())
                || user.getOtpExpiry().isBefore(Instant.now())) {
            throw new CustomException("Invalid or expired OTP");
        }

        user.setEmailVerified(true);
        user.setOtp(null);
        user.setOtpExpiry(null);

        userRepository.save(user);

        log.info("end AuthServiceImpl --> verifyOtp");
    }

    @Override
    public AuthResponse login(String email) {

        log.info("begin AuthServiceImpl --> login");

        User user = userRepository.findByEmailAndActiveTrue(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        if (!user.isEmailVerified()) {
            throw new CustomException("Email not verified");
        }

        String token = jwtUtil.generateToken(user.getId(), user.getEmail());

        log.info("end AuthServiceImpl --> login");

        return new AuthResponse(token, email);
    }

    private String generateOtp() {
        return String.valueOf(100000 + new Random().nextInt(900000));
    }
}
