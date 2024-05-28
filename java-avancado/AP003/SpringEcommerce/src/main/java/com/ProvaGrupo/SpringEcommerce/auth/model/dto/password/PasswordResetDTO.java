package com.ProvaGrupo.SpringEcommerce.auth.model.dto.password;

import com.ProvaGrupo.SpringEcommerce.auth.util.validator.ValidPassword;

/**
 * It is a DTO that represents the data needed to reset a user's password, contains the password and the confirmation password.
 * It is used to transfer data between the controller and the service.
 * @see PasswordResetRequestDTO 
 */
public record PasswordResetDTO (
                        @ValidPassword String password, 
                        String confirmPassword) {
        
}
