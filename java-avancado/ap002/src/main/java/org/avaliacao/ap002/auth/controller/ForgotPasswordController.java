package org.avaliacao.ap002.auth.controller;

import jakarta.validation.Valid;
import org.avaliacao.ap002.auth.entity.ForgotPassword;
import org.avaliacao.ap002.auth.entity.MailBody;
import org.avaliacao.ap002.auth.entity.user.User;
import org.avaliacao.ap002.auth.repository.ForgotPasswordRepository;
import org.avaliacao.ap002.auth.repository.UserRepository;
import org.avaliacao.ap002.auth.service.EmailService;
import org.avaliacao.ap002.auth.utils.ChangePassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

@RestController
@RequestMapping("/forgotPassword")
public class ForgotPasswordController {
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final ForgotPasswordRepository forgotPasswordRepository;
    private final PasswordEncoder passwordEncoder;

    public ForgotPasswordController(UserRepository userRepository, EmailService emailService, ForgotPasswordRepository forgotPasswordRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.forgotPasswordRepository = forgotPasswordRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/verifyEmail/{email}")
    public ResponseEntity<String>verifyEmail(@PathVariable String email){
        User user =(User) userRepository.findByEmail(email);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        int otp = otpGenerator();
        MailBody mailBody = MailBody.builder()
                .to(email)
                .text("This is your OTP for your Forgot Password request: "+ otp)
                .subject("OTP for Forgot Password Request")
                .build();

        ForgotPassword forgotPassword = ForgotPassword.builder()
                .oneTimePass(otp)
                .expirationTime(new Date(System.currentTimeMillis()+70*1000))
                .user(user)
                .build();
        emailService.sendSimpleMessage(mailBody);
        forgotPasswordRepository.save(forgotPassword);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/verifyOtp/{otp}/{email}")
    public ResponseEntity<String>verifyOtp(@PathVariable Integer otp, @PathVariable String email){
        User user = (User) userRepository.findByEmail(email);
        ForgotPassword forgotPassword = forgotPasswordRepository.findByOneTimePassAndUser(otp, user).orElseThrow(()->new RuntimeException("Invalid otp for email: " + email));
        if (forgotPassword.getExpirationTime().before(Date.from(Instant.now()))) {
            forgotPasswordRepository.deleteById(forgotPassword.getForgotPassID());
            return new ResponseEntity<>("Otp has expired", HttpStatus.EXPECTATION_FAILED);
        }
        else
            return ResponseEntity.ok("Otp verified");
    }

    @PostMapping("/changePassword/{email}")
    public ResponseEntity<String>changePasswordHandler(@RequestBody ChangePassword changePassword, @PathVariable @Valid String email){
        if(!Objects.equals(changePassword.password(), changePassword.repeatPassword()))
            return new ResponseEntity<>("Please enter the same password", HttpStatus.EXPECTATION_FAILED);

        String encodedPassword = passwordEncoder.encode(changePassword.password());
        userRepository.updatePassword(email, encodedPassword);
        return ResponseEntity.ok("Password has been changed");
    }

    private Integer otpGenerator(){
        Random random = new Random();
        return random.nextInt(100_000, 999_999);
    }

}
