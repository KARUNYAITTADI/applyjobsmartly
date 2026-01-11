package com.applyjobsmartly.ajs.serviceImpl;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.applyjobsmartly.ajs.service.EmailService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Override
    public void sendOtpEmail(String to, String otp) {

        log.info("begin EmailServiceImpl --> sendOtpEmail");

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject("ApplyJobSmartly - Email Verification");
            message.setText(
                    "Your OTP for email verification is: " + otp +
                    "\n\nThis OTP is valid for 5 minutes."
            );

            mailSender.send(message);

        } catch (Exception e) {
            log.error("Failed to send OTP email", e);
            throw new RuntimeException("Email sending failed");
        }

        log.info("end EmailServiceImpl --> sendOtpEmail");
    }
}
