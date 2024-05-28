package com.ProvaGrupo.SpringEcommerce.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class VerificationTokenTest {

	private static Validator validator;

	@BeforeAll
	static void setUpValidator() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	void testConstructorWithParameters() {
		Long id = 1L;
		String tokenValue = "testToken123";
		Long userId = 2L;
		Instant expiryDate = Instant.now().plusSeconds(3600);

		VerificationToken token = new VerificationToken(id, tokenValue, userId, expiryDate);

		assertAll(() -> assertEquals(id, token.getId()), () -> assertEquals(tokenValue, token.getToken()),
				() -> assertEquals(userId, token.getUserId()), () -> assertEquals(expiryDate, token.getExpiryDate()));
	}

	@Test
	void testEqualsAndHashCodeForEqualObjects() {
		VerificationToken token1 = new VerificationToken(1L, "tokenString", 2L, Instant.now());
		VerificationToken token2 = new VerificationToken(1L, "tokenString", 2L, token1.getExpiryDate());

		assertThat(token1).isEqualTo(token2).hasSameHashCodeAs(token2);
	}

	@Test
	void testEqualsAndHashCodeForUnequalObjects() {
		VerificationToken token1 = new VerificationToken(1L, "tokenString", 2L, Instant.now());
		VerificationToken token2 = new VerificationToken(3L, "otherToken", 4L, Instant.now());

		assertThat(token1).isNotEqualTo(token2).doesNotHaveSameHashCodeAs(token2);
	}

	@ParameterizedTest
	@ValueSource(strings = { "", "    " }) // Testes com strings vazias e espaços em branco
	void testTokenValidationWithInvalidValues(String invalidToken) {
		VerificationToken token = new VerificationToken(1L, invalidToken, 2L, Instant.now());
		Set<ConstraintViolation<VerificationToken>> violations = validator.validate(token);
		assertThat(violations).hasSize(1);
		assertThat(violations.iterator().next().getMessage()).isEqualTo("Token obrigatório");
	}

	@Test
	void testUserIdValidationWithNullValue() {
		VerificationToken token = new VerificationToken(1L, "validToken", null, Instant.now());
		Set<ConstraintViolation<VerificationToken>> violations = validator.validate(token);
		assertThat(violations).hasSize(1).extracting(ConstraintViolation::getMessage).containsOnly("User obrigatório");

	}

	@Test
	void testToString() {
		VerificationToken token = new VerificationToken(1L, "tokenString", 2L, Instant.now());
		String expectedString = "VerificationToken(id=1, token=tokenString, userId=2, expiryDate="
				+ token.getExpiryDate() + ")";
		assertThat(token.toString()).isEqualTo(expectedString);
	}

	@ParameterizedTest
	@ValueSource(ints = { -3600, 0, 3600 }) // Expirado, Expira agora, Válido
	void testExpiryDate(int seconds) {
		Instant now = Instant.now();
		VerificationToken token = new VerificationToken(1L, "tokenString", 2L, now.plusSeconds(seconds));

		if (seconds < 0) {
			assertTrue(token.getExpiryDate().isBefore(now));
		} else {
			assertFalse(token.getExpiryDate().isBefore(now));
		}
	}

	@Test
	void testExpiryDateValidationWithNullValue() { // Nome do teste mais adequado
		VerificationToken token = new VerificationToken(1L, "validToken", 2L, null); // expiryDate nulo
		Set<ConstraintViolation<VerificationToken>> violations = validator.validate(token);

		assertThat(violations).hasSize(1).extracting(ConstraintViolation::getMessage)
				.containsOnly("ExpiryDate obrigatório");
	}
}
