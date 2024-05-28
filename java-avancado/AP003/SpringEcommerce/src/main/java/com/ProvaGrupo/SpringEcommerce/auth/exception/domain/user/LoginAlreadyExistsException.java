package com.ProvaGrupo.SpringEcommerce.auth.exception.domain.user;

import java.util.Locale;
import java.util.ResourceBundle;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginAlreadyExistsException extends RuntimeException {

    private final static ResourceBundle bundle = ResourceBundle.getBundle("messages", Locale.getDefault());    

    public LoginAlreadyExistsException(String login) {
        super(bundle.getString("user.login_already_exists").replace("{login}", login));
        log.error("User with login {} already exists", login);
    }
    
}
