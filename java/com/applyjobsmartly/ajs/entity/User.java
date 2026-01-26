package com.applyjobsmartly.ajs.entity;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends BaseEntity {

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "otp")
    private String otp;

    @Column(name = "otp_expiry")
    private Instant otpExpiry;

    @Column(name = "email_verified")
    private boolean emailVerified;

    @Column(name = "role")
    private String role;
}
