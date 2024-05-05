package org.avaliacao.ap002.auth.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.avaliacao.ap002.auth.entity.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secretKey}")
    private String secretKey;

    public String generateToken(User user){
        try {
            Algorithm algorithm = Algorithm.HMAC384(secretKey);
            String token = JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(user.getEmail())
                    .withExpiresAt(genExpirationInstant())
                    .sign(algorithm);
            return token;
         }catch(JWTCreationException e){
            throw new RuntimeException("Error while generation token", e);
        }

    }
    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC384(secretKey);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException e){
            return "";
        }
    }
    public Instant genExpirationInstant(){
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));
    }
}
