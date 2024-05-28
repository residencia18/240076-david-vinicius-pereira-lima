package com.ProvaGrupo.SpringEcommerce.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ProvaGrupo.SpringEcommerce.auth.exception.domain.authentication.InvalidCredentialsException;
import com.ProvaGrupo.SpringEcommerce.auth.exception.domain.authentication.InvalidOtpException;
import com.ProvaGrupo.SpringEcommerce.auth.exception.domain.user.EmailAlreadyExistsException;
import com.ProvaGrupo.SpringEcommerce.auth.exception.domain.user.LoginAlreadyExistsException;
import com.ProvaGrupo.SpringEcommerce.auth.exception.domain.user.UserAlreadyVerifiedException;
import com.ProvaGrupo.SpringEcommerce.auth.exception.domain.user.UserNotEnabledException;
import com.ProvaGrupo.SpringEcommerce.auth.exception.domain.user.UserNotFoundException;
import com.ProvaGrupo.SpringEcommerce.auth.infra.security.TokenService;
import com.ProvaGrupo.SpringEcommerce.auth.model.OneTimePassword;
import com.ProvaGrupo.SpringEcommerce.auth.model.User;
import com.ProvaGrupo.SpringEcommerce.auth.model.UserRole;
import com.ProvaGrupo.SpringEcommerce.auth.model.dto.authentication.LoginDTO;
import com.ProvaGrupo.SpringEcommerce.auth.model.dto.authentication.LoginResponseDTO;
import com.ProvaGrupo.SpringEcommerce.auth.model.dto.authentication.SignupDTO;
import com.ProvaGrupo.SpringEcommerce.auth.repository.UserRepository;
import com.ProvaGrupo.SpringEcommerce.auth.util.EmailUtil;
import com.ProvaGrupo.SpringEcommerce.auth.util.OtpUtil;

import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;

/**
 * This service is responsible for handling the authentication operations. 
 * 
 */
@Service
@Slf4j
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailUtil emailUtil;

    @Autowired
    private OtpUtil otpUtil;

    /**
     * This method will authenticate the user and generate a token for it.
     * Throws an exception if the user is disabled or the credentials are invalid. 
     * 
     * @param data Data to login.
     * @throws UserNotEnabledException if the user is disabled.
     * @throws InvalidCredentialsException if the credentials are invalid.
     * @return Token generated for the user.
     * 
     */
    public LoginResponseDTO login(LoginDTO data) {
        log.info("Received data to login");

        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
            var auth = authenticationManager.authenticate(usernamePassword);

            var token = tokenService.generateToken((User) auth.getPrincipal());

            LoginResponseDTO response = new LoginResponseDTO(token);

            log.info("User logged in: {}", response);

            return response;
        } catch (DisabledException ex) {
            throw new UserNotEnabledException();
        } catch (AuthenticationException ex) {
            throw new InvalidCredentialsException();
        } catch (Exception ex) {
            log.error("Error occurred during login");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error occurred during login");
        }
    }

    /**
     * This method will register a new user in the system.
     * Throws an exception if the login or email already exists.
     * 
     * @param data Data to register.
     * @throws LoginAlreadyExistsException if the login already exists.
     * @throws EmailAlreadyExistsException if the email already exists.
     * 
     */
    public void signup(SignupDTO data) {
        log.info("Received data to signup");

        if (userRepository.existsByUsername(data.username())) {
            throw new LoginAlreadyExistsException(data.username());
        }

        if (userRepository.existsByEmail(data.email())) {
            throw new EmailAlreadyExistsException(data.email());
        }

        OneTimePassword oneTimePassword = otpUtil.generateOtp();
        try {
            emailUtil.sendOtpEmail(data.email(), oneTimePassword.otp());
        } catch (MessagingException ex) {
            log.error("Error occurred while sending email to verify account");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error occurred while sending email to verify account");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

        User user = User.builder()
                .username(data.username())
                .password(encryptedPassword)
                .email(data.email())
                .role(UserRole.USER)
                .otp(oneTimePassword)
                .build();

        log.info("New user created: {}", data.username());

        userRepository.save(user);
    }

    /**
     * This method will verify the account of the user.
     * Throws an exception if the user is already verified, the OTP is invalid or expired or the user is not found.
     * @param email The email of the user.
     * @param otp The OTP to verify the account.
     * @throws UserAlreadyVerifiedException if the user is already verified.
     * @throws InvalidOtpException if the OTP is invalid or expired.
     * 
     */
    public void verifyAccount(String email, String otp) {
        log.info("Received email and OTP to verify account");

        userRepository.findByEmail(email)
                .ifPresentOrElse(user -> {
                    log.info("User found: {}", user.getUsername());
                    if (user.isEnabled()) {
                        throw new UserAlreadyVerifiedException();
                    }

                    if (user.getOtp().otp().equals(otp) && otpUtil.isValidOtp(user.getOtp())) {
                        user.setEnabled(true);
                        user.setOtp(null); 
                        userRepository.save(user);
                        log.info("User account verified");
                    } else {
                        throw new InvalidOtpException(new Throwable("Invalid or expired OTP"));
                    }
                }, () -> {
                    throw new UserNotFoundException(email);
                });
    }

    /**
     * This method will resend the verification email to the user.
     * Throws an exception if the user is already verified or the user is not found.
     * @param email The email of the user.
     * @throws UserAlreadyVerifiedException if the user is already verified.
     * @throws UserNotFoundException if the user is not found.
     * 
     */
    public void resendVerification(String email) {
        log.info("Received email to resend verification email");

        userRepository.findByEmail(email)
                .ifPresentOrElse(user -> {
                    log.info("User found: {}", user.getUsername());
                    if (user.isEnabled()) {
                        throw new UserAlreadyVerifiedException();
                    }

                    OneTimePassword oneTimePassword = otpUtil.generateOtp();
                    try {
                        emailUtil.sendOtpEmail(email, oneTimePassword.otp());
                    } catch (MessagingException ex) {
                        log.error("Error occurred while sending email to verify account");
                        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                                "Error occurred while sending email to verify account");
                    }
                    user.setOtp(oneTimePassword); 
                    userRepository.save(user);
                }, () -> {
                    throw new UserNotFoundException(email);
                });
    }

}