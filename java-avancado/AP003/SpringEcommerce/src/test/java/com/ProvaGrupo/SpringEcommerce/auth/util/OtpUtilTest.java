package com.ProvaGrupo.SpringEcommerce.auth.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;

import com.ProvaGrupo.SpringEcommerce.auth.model.OneTimePassword;

public class OtpUtilTest {

    private OtpUtil otpUtil = new OtpUtil();

    @Test
    public void testGenerateOtp() {
        OneTimePassword otp = otpUtil.generateOtp();

        assertNotNull(otp, "OTP should not be null");
        assertEquals(6, otp.otp().length(), "OTP should be 6 digits long");
        assertNotNull(otp.otpGenerationTime(), "OTP generation time should not be null");
    }

    @Test
    public void testIsValidOtp() {
        OneTimePassword otp = otpUtil.generateOtp();

        assertTrue(otpUtil.isValidOtp(otp), "OTP should be valid right after generation");

        OneTimePassword expiredOtp = new OneTimePassword(otp.otp(), LocalDateTime.now().minus(6, ChronoUnit.MINUTES));

        assertFalse(otpUtil.isValidOtp(expiredOtp), "OTP should be invalid after 5 minutes");
    }
}