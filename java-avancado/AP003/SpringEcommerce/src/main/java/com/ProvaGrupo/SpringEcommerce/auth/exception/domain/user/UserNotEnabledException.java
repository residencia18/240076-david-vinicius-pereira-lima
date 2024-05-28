package com.ProvaGrupo.SpringEcommerce.auth.exception.domain.user;

import java.util.Locale;
import java.util.ResourceBundle;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserNotEnabledException extends RuntimeException{
    
    private final static ResourceBundle bundle = ResourceBundle.getBundle("messages", Locale.getDefault());

    public UserNotEnabledException() {
        super(bundle.getString("user.not_enabled"));
        log.error(bundle.getString("user.not_enabled"));
    }
    
    public UserNotEnabledException(Throwable cause) {
        super(bundle.getString("user.not_enabled"), cause);
        log.error(bundle.getString("user.not_enabled"), cause);
    }
}
