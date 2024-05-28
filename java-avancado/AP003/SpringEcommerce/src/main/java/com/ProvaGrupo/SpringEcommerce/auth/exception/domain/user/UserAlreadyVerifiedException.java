package com.ProvaGrupo.SpringEcommerce.auth.exception.domain.user;

import java.util.Locale;
import java.util.ResourceBundle;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserAlreadyVerifiedException extends RuntimeException{
    
    private final static ResourceBundle bundle = ResourceBundle.getBundle("messages", Locale.getDefault());

    public UserAlreadyVerifiedException() {
        super(bundle.getString("user.already_verified"));
        log.error(bundle.getString("user.already_verified"));
    }
    
    public UserAlreadyVerifiedException(Throwable cause) {
        super(bundle.getString("user.already_verified"), cause);
        log.error(bundle.getString("user.already_verified"), cause);
    }
}
