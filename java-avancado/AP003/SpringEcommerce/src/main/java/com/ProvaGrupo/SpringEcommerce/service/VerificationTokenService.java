package com.ProvaGrupo.SpringEcommerce.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.ProvaGrupo.SpringEcommerce.auth.infra.security.TokenService;
import com.ProvaGrupo.SpringEcommerce.auth.model.User;
import com.ProvaGrupo.SpringEcommerce.model.VerificationToken;
import com.ProvaGrupo.SpringEcommerce.repository.VerificationTokenRepository;

@Service
public class VerificationTokenService {

	@Autowired
	private VerificationTokenRepository tokenRepository;

	@Autowired
	private TokenService jwtProviderService;

	@Value("${auth.security.token.expiration-time}")
    private static long EXPIRATION_TIME_IN_SECONDS;
	
	public VerificationToken createToken(Authentication authentication, Long userId) {
		String tokenValue = jwtProviderService.generateToken((User) authentication.getPrincipal());
		
		
		VerificationToken token = new VerificationToken();
		token.setToken(tokenValue);
		token.setUserId(userId);
		token.setExpiryDate(Instant.now().plusSeconds(EXPIRATION_TIME_IN_SECONDS));

		return tokenRepository.save(token);
	}

	public Optional<VerificationToken> getToken(String token) {
		return tokenRepository.findByToken(token);
	}

	public void deleteToken(VerificationToken token) {
		tokenRepository.delete(token);
	}

	public void deleteExpiredTokens() {
		tokenRepository.deleteByExpiryDateBefore(Instant.now());
	}

	public boolean isTokenExpired(VerificationToken token) {
		return token.getExpiryDate().isBefore(Instant.now());
	}

	public void updateExpiryDate(VerificationToken token, Instant newExpiryDate) {
		token.setExpiryDate(newExpiryDate);
		tokenRepository.save(token);
	}

	public List<VerificationToken> getTokensByUserId(Long userId) {
		return tokenRepository.findByUserId(userId);
	}

}
