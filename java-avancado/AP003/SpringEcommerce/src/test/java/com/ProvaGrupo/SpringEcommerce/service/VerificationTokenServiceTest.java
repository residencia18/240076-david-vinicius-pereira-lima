package com.ProvaGrupo.SpringEcommerce.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.ProvaGrupo.SpringEcommerce.auth.infra.security.TokenService;
import com.ProvaGrupo.SpringEcommerce.model.VerificationToken;
import com.ProvaGrupo.SpringEcommerce.repository.VerificationTokenRepository;

@ExtendWith(MockitoExtension.class)
class VerificationTokenServiceTest {

    @Mock
    private VerificationTokenRepository tokenRepository;

    @Mock
    private TokenService jwtProviderService;

    @InjectMocks
    private VerificationTokenService tokenService;

    private Authentication authentication;

    @BeforeEach
    void setUp() {
        authentication = new UsernamePasswordAuthenticationToken("user", "password");
    }

    @Test
    void testGetToken() {
        VerificationToken token = new VerificationToken(1L, "tokenValue", 1L, Instant.now());
        when(tokenRepository.findByToken("tokenValue")).thenReturn(Optional.of(token));

        Optional<VerificationToken> result = tokenService.getToken("tokenValue");

        assertTrue(result.isPresent());
        assertEquals(token, result.get());
    }

    @Test
    void testDeleteToken() {
        VerificationToken token = new VerificationToken(1L, "tokenValue", 1L, Instant.now());

        tokenService.deleteToken(token);

        verify(tokenRepository, times(1)).delete(token);
    }
}