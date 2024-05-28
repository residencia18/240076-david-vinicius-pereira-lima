package com.ProvaGrupo.SpringEcommerce.auth.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class RestErrorMessageTest {

    private RestErrorMessage restErrorMessage;

    @BeforeEach
    public void setUp() {
        restErrorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST, "Bad Request");
    }

    @Test
    public void testGetStatus() {
        assertEquals(HttpStatus.BAD_REQUEST, restErrorMessage.getStatus(), "The status should be BAD_REQUEST");
    }

    @Test
    public void testSetStatus() {
        restErrorMessage.setStatus(HttpStatus.NOT_FOUND);
        assertEquals(HttpStatus.NOT_FOUND, restErrorMessage.getStatus(), "The status should be NOT_FOUND");
    }

    @Test
    public void testGetMessage() {
        assertEquals("Bad Request", restErrorMessage.getMessage(), "The message should be 'Bad Request'");
    }

    @Test
    public void testSetMessage() {
        restErrorMessage.setMessage("Not Found");
        assertEquals("Not Found", restErrorMessage.getMessage(), "The message should be 'Not Found'");
    }
}