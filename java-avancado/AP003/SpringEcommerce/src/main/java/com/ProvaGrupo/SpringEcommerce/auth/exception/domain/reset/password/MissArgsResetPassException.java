package com.ProvaGrupo.SpringEcommerce.auth.exception.domain.reset.password;

import java.util.Locale;
import java.util.ResourceBundle;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MissArgsResetPassException extends RuntimeException{
    
    private final static ResourceBundle bundle = ResourceBundle.getBundle("messages", Locale.getDefault());

    public MissArgsResetPassException() {
        super(bundle.getString("password_reset.arguments_missing"));
        log.error(bundle.getString("password_reset.arguments_missing"));
    }
    
    public MissArgsResetPassException(Throwable cause) {
        super(bundle.getString("password_reset.arguments_missing"), cause);
        log.error(bundle.getString("password_reset.arguments_missing"), cause);
    }
}