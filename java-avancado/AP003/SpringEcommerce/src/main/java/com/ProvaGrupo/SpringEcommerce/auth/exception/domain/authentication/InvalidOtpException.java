package com.ProvaGrupo.SpringEcommerce.auth.exception.domain.authentication;

import java.util.Locale;
import java.util.ResourceBundle;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InvalidOtpException extends RuntimeException {

    private final static ResourceBundle bundle = ResourceBundle.getBundle("messages", Locale.getDefault());

    public InvalidOtpException() {
        super(bundle.getString("auth.invalid_otp"));
        log.error(bundle.getString("auth.invalid_otp"));
    }

    public InvalidOtpException(Throwable cause) {
        super(bundle.getString("auth.invalid_otp"), cause);
        log.error(bundle.getString("auth.invalid_otp"), cause);
    }
}
