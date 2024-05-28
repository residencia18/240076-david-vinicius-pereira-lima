package com.ProvaGrupo.SpringEcommerce.auth.controller;

import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ProvaGrupo.SpringEcommerce.auth.model.dto.password.PasswordResetDTO;
import com.ProvaGrupo.SpringEcommerce.auth.model.dto.password.PasswordResetRequestDTO;
import com.ProvaGrupo.SpringEcommerce.auth.service.PasswordResetService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * Controller class for password reset endpoints. It handles the HTTP requests related to password reset.
 * It uses the PasswordResetService to perform operations on the database.
 * 
 * @see PasswordResetService
 */
@Slf4j
@RestController
@RequestMapping("/password")
@Tag(name = "Password Reset", description = "Operations related to password reset")
public class PasswordResetController {

    @Autowired
    private PasswordResetService passwordResetService;

    private final ResourceBundle bundle = ResourceBundle.getBundle("messages", Locale.getDefault());

    /**
     * Request a password reset for the given email.
     * @param data The email of the user to request a password reset, passed as a request body.
     * @return The response containing a message indicating that the password reset was requested successfully.
     */
    @Operation(summary = "Request a password reset", description = "Request a password reset for the given email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Password reset requested successfully"),
            @ApiResponse(responseCode = "404", description = "User not found with the given email"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/request-reset")
    public ResponseEntity<String> requestReset(
            @RequestBody @Valid PasswordResetRequestDTO data) {
                
        log.info("Requesting password reset for email: {}", data.email());
        passwordResetService.requestReset(data);
        log.info("Password reset requested successfully for email: {}", data.email());
        return ResponseEntity.ok(bundle.getString("password_reset.requested"));
    }

    /**
     * Reset a password for the given email and token.
     * @param email The email of the user to reset the password.
     * @param token The token that was sent to the user's email. 
     * @param data The new password and the confirmation password, passed as a request body.
     * @return The response containing a message indicating that the password was reset successfully.
     */
    @Operation(summary = "Reset a password", description = "Reset a password for the given email and token")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Password reset successfully"),
        @ApiResponse(responseCode = "400", description = "OTP and new password must be provided / passwords do not match"),
        @ApiResponse(responseCode = "401", description = "Invalid or expired OTP"),        
        @ApiResponse(responseCode = "404", description = "User not found with the given email"),
        @ApiResponse(responseCode = "409", description = "Passwords do not match"),
    })
    @PostMapping("/reset")
    public ResponseEntity<String> reset(
            @RequestParam String email,
            @RequestParam String token,
            @RequestBody @Valid PasswordResetDTO data) {

        log.info("Resetting password for email: {}", email);
        passwordResetService.reset(email, token, data);
        log.info("Password reset successfully for email: {}", email);
        return ResponseEntity.ok(bundle.getString("password_reset.successful"));
    }
}
