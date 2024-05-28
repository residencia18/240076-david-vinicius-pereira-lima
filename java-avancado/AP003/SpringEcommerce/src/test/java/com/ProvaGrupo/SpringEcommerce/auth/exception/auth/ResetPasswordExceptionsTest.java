package com.ProvaGrupo.SpringEcommerce.auth.exception.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ProvaGrupo.SpringEcommerce.auth.exception.RestErrorMessage;
import com.ProvaGrupo.SpringEcommerce.auth.exception.domain.reset.password.MissArgsResetPassException;
import com.ProvaGrupo.SpringEcommerce.auth.exception.domain.reset.password.PasswordsDoNotMatchException;
import com.ProvaGrupo.SpringEcommerce.auth.exception.handler.ResetPasswordExceptionsHandler;

public class ResetPasswordExceptionsTest {

    private ResetPasswordExceptionsHandler resetPasswordExceptionsHandler;

    private ResourceBundle bundle;

    @BeforeEach
    public void init() {
        resetPasswordExceptionsHandler = new ResetPasswordExceptionsHandler();
        bundle = ResourceBundle.getBundle("messages", Locale.getDefault());
    }
    
    @Test
    public void testHandleMissingArgumentsToResetPasswordException() {
        MissArgsResetPassException missingArgumentsToResetPasswordException = new MissArgsResetPassException();
        String expectedMessage = bundle.getString("password_reset.arguments_missing");

        ResponseEntity<RestErrorMessage> responseEntity = resetPasswordExceptionsHandler.handleMissingArgumentsToResetPasswordException(missingArgumentsToResetPasswordException);

        // Assert that the response status is BAD_REQUEST and the message is the expected one
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(expectedMessage, responseEntity.getBody().getMessage());
    }

    @Test
    public void testPasswordsDoNotMatchException() {
        PasswordsDoNotMatchException exception = new PasswordsDoNotMatchException();
        String expectedMessage = bundle.getString("password_reset.must_match");

        ResponseEntity<RestErrorMessage> responseEntity = resetPasswordExceptionsHandler.handlePasswordsDoNotMatchException(exception);

        // Assert that the response status is BAD_REQUEST and the message is the expected one
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(expectedMessage, responseEntity.getBody().getMessage());
    }
}
