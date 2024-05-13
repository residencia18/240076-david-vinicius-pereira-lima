package org.avaliacao.ap002.test_controller;

import org.avaliacao.ap002.auth.controller.ForgotPasswordController;
import org.avaliacao.ap002.auth.entity.ForgotPassword;
import org.avaliacao.ap002.auth.entity.MailBody;
import org.avaliacao.ap002.auth.entity.user.User;
import org.avaliacao.ap002.auth.entity.user.UserRole;
import org.avaliacao.ap002.auth.repository.ForgotPasswordRepository;
import org.avaliacao.ap002.auth.repository.UserRepository;
import org.avaliacao.ap002.auth.service.EmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ForgetPasswordControllerTest {
    private ForgotPasswordController forgotPasswordController;
    private UserRepository userRepository;
    private EmailService emailService;
    private ForgotPasswordRepository forgotPasswordRepository;
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {
        userRepository = mock(UserRepository.class);
        emailService = mock(EmailService.class);
        forgotPasswordRepository = mock(ForgotPasswordRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        forgotPasswordController = new ForgotPasswordController(userRepository, emailService, forgotPasswordRepository, passwordEncoder);
    }

    @Test
    public void testVerifyEmail_Success() {
        User user = new User("test@example.com", "password", UserRole.USER);
        when(userRepository.findByEmail("test@example.com")).thenReturn(user);
        when(forgotPasswordRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        ResponseEntity<String> response = forgotPasswordController.verifyEmail("test@example.com");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(emailService, times(1)).sendSimpleMessage(any(MailBody.class));
        verify(forgotPasswordRepository, times(1)).save(any(ForgotPassword.class));
    }

    @Test
    public void testVerifyEmail_UserNotFound() {
        when(userRepository.findByEmail("nonexistent@example.com")).thenReturn(null);

        ResponseEntity<String> response = forgotPasswordController.verifyEmail("nonexistent@example.com");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verifyNoInteractions(emailService);
        verifyNoInteractions(forgotPasswordRepository);
    }

    @Test
    public void testVerifyOtp_Success() {
        User user = new User("test@example.com", "password", UserRole.USER);
        ForgotPassword forgotPassword = new ForgotPassword(1L, 123456, Date.from(Instant.now().plusSeconds(60)), user);
        when(userRepository.findByEmail("test@example.com")).thenReturn(user);
        when(forgotPasswordRepository.findByOneTimePassAndUser(123456, user)).thenReturn(Optional.of(forgotPassword));

        ResponseEntity<String> response = forgotPasswordController.verifyOtp(123456, "test@example.com");

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testVerifyOtp_InvalidOtp() {
        User user = new User("test@example.com", "password", UserRole.USER);
        when(userRepository.findByEmail("test@example.com")).thenReturn(user);
        when(forgotPasswordRepository.findByOneTimePassAndUser(123456, user)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> forgotPasswordController.verifyOtp(123456, "test@example.com"));
    }
}
