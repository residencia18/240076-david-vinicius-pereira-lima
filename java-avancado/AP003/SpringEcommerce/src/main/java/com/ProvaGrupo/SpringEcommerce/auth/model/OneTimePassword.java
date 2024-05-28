package com.ProvaGrupo.SpringEcommerce.auth.model;

import java.time.LocalDateTime;

import jakarta.persistence.Embeddable;

/**
 * Represents a one-time password and the time it was generated. 
 * OTPs are used for account verification and password reset.
 */
@Embeddable
public record OneTimePassword(String otp, LocalDateTime otpGenerationTime) {

    public OneTimePassword {
        if (otp == null || otpGenerationTime == null) {
            throw new IllegalArgumentException("OTP and generation time must not be null");
        }
    }
}
    