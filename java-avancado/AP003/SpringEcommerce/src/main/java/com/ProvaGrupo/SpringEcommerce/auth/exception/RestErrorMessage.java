package com.ProvaGrupo.SpringEcommerce.auth.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * RestErrorMessage class.
 * This class is responsible for creating a message to be returned in the response body when an exception is thrown.
 * It contains the status and the message.
 */
@AllArgsConstructor
@Getter
@Setter
public class RestErrorMessage {

    /**
     * Status to be returned in the response.
     */
    private HttpStatus status;

    /**
     * Message to be returned in the response body.
     */
    private String message;

}
