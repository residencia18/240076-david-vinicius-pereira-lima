package com.ProvaGrupo.SpringEcommerce.auth.exception.domain.reset.password;

import java.util.Locale;
import java.util.ResourceBundle;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PasswordsDoNotMatchException extends RuntimeException{
    
    private final static ResourceBundle bundle = ResourceBundle.getBundle("messages", Locale.getDefault());

    public PasswordsDoNotMatchException() {
        super(bundle.getString("password_reset.must_match"));
        log.error(bundle.getString("password_reset.must_match"));
    }
    
    public PasswordsDoNotMatchException(Throwable cause) {
        super(bundle.getString("password_reset.must_match"), cause);
        log.error(bundle.getString("password_reset.must_match"), cause);
    }
}
