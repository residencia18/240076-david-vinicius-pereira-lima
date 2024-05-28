package com.ProvaGrupo.SpringEcommerce.auth.exception.domain.user;

import java.util.Locale;
import java.util.ResourceBundle;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserNotFoundException extends RuntimeException {

    private final static ResourceBundle bundle = ResourceBundle.getBundle("messages", Locale.getDefault());

    public UserNotFoundException(Long id) {
        super(bundle.getString("user.with_id_not_found").replace("{id}", id.toString()));
        log.error("User with id {} not found", id);
    }

    public UserNotFoundException(String email) {
        super(bundle.getString("user.with_email_not_found").replace("{email}", email));
        log.error("User with email {} not found", email);
    }
}
