package org.avaliacao.ap002.auth.controller;

import jakarta.validation.Valid;
import org.avaliacao.ap002.auth.entity.user.AuthDTO;
import org.avaliacao.ap002.auth.entity.user.LoginResponseDTO;
import org.avaliacao.ap002.auth.entity.user.RegisterDTO;
import org.avaliacao.ap002.auth.entity.user.User;
import org.avaliacao.ap002.auth.repository.UserRepository;
import org.avaliacao.ap002.auth.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?>login(@RequestBody @Valid AuthDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }


    @PostMapping("/register")
    public ResponseEntity<?>register(@RequestBody @Valid RegisterDTO data){
        if (this.userRepository.findByEmail(data.email())!=null)
            return ResponseEntity.badRequest().build();
        else{
            String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
            User newUser = new User(data.email(), encryptedPassword, data.role());

            this.userRepository.save(newUser);
            return ResponseEntity.ok().build();
        }
    }

}
