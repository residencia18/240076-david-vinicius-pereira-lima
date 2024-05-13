package org.avaliacao.ap002.test_service;

import com.auth0.jwt.exceptions.JWTVerificationException;
import org.avaliacao.ap002.auth.entity.user.User;
import org.avaliacao.ap002.auth.entity.user.UserRole;
import org.avaliacao.ap002.auth.service.TokenService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TokenServiceTest {
    private final String secretKey = "testSecretKey";
    private final TokenService tokenService = new TokenService();

    @Test
    public void testGenerateToken_Success() {
        User user = new User("test@example.com", "password", UserRole.USER);
        String token = tokenService.generateToken(user);

        assertNotNull(token);
        assertTrue(token.length() > 0);
    }

    @Test
    public void testValidateToken_Success() {
        User user = new User("test@example.com", "password", UserRole.USER);
        String token = tokenService.generateToken(user);

        String subject = tokenService.validateToken(token);

        assertEquals(user.getEmail(), subject);
    }

    @Test
    public void testValidateToken_InvalidToken() {
        assertThrows(JWTVerificationException.class, () -> tokenService.validateToken("invalidToken"));
    }

    @Test
    public void testGenExpirationInstant() {
        Instant expirationInstant = tokenService.genExpirationInstant();

        Instant expectedInstant = LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));
        assertTrue(expirationInstant.isAfter(Instant.now()));
        assertTrue(expirationInstant.isBefore(expectedInstant));
    }
}
