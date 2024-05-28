package com.ProvaGrupo.SpringEcommerce.auth.model.dto.authentication;

/**
 * It is a DTO that represents the data needed to register a user, contains the login, password, email, birth date and mobile phone.
 * It is used to transfer data between the controller and the service.
 */
public record SignupDTO(
        String username,
        String password,
        String email
) {

}
