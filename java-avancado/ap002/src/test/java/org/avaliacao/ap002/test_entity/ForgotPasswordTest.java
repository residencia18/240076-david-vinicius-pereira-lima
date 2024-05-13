package org.avaliacao.ap002.test_entity;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.avaliacao.ap002.auth.entity.ForgotPassword;
import org.avaliacao.ap002.auth.entity.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ForgotPasswordTest {
    private ForgotPassword forgotPassword;
    private Validator validator;

    @BeforeEach
    public void setUp() {
        forgotPassword = ForgotPassword.builder()
                .forgotPassID(1L)
                .oneTimePass(123456)
                .expirationTime(new Date())
                .user(new User())
                .build();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testForgotPassword_Valid() {
        assertTrue(validator.validate(forgotPassword).isEmpty());
    }

    @Test
    public void testForgotPassword_OneTimePassNull() {
        forgotPassword.setOneTimePass(null);
        assertFalse(validator.validate(forgotPassword).isEmpty());
    }

    @Test
    public void testForgotPassword_ExpirationTimeNull() {
        forgotPassword.setExpirationTime(null);
        assertFalse(validator.validate(forgotPassword).isEmpty());
    }

    @Test
    public void testForgotPassword_UserNull() {
        forgotPassword.setUser(null);
        assertFalse(validator.validate(forgotPassword).isEmpty());
    }
}
