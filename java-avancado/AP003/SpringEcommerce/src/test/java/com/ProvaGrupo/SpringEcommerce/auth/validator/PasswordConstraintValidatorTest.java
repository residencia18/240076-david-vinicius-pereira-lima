package com.ProvaGrupo.SpringEcommerce.auth.validator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ProvaGrupo.SpringEcommerce.auth.util.validator.PasswordConstraintValidator;

import jakarta.validation.ConstraintValidatorContext;

public class PasswordConstraintValidatorTest {

    private PasswordConstraintValidator validator;

    @Mock
    private ConstraintValidatorContext constraintValidatorContext;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        validator = new PasswordConstraintValidator();
    }

    @Test
    public void testValidPassword() {
        assertTrue(validator.isValid("ValidPassword123!", constraintValidatorContext));
    }

    @Test
    public void testInvalidShortPassword() {
        assertFalse(validator.isValid("Abc1!", constraintValidatorContext));
    }

    @Test
    public void testInvalidNoUppercasePassword() {
        assertFalse(validator.isValid("abc123!@", constraintValidatorContext));
    }

    @Test
    public void testInvalidNoLowercasePassword() {
        assertFalse(validator.isValid("ABC123!@", constraintValidatorContext));
    }

    @Test
    public void testInvalidNoDigitPassword() {
        assertFalse(validator.isValid("Abcdefgh!", constraintValidatorContext));
    }

    @Test
    public void testInvalidNoSpecialCharacterPassword() {
        assertFalse(validator.isValid("Abcdefg123", constraintValidatorContext));
    }

    @Test
    public void testInvalidNullPassword() {
        assertFalse(validator.isValid(null, constraintValidatorContext));
    }

    @Test
    public void testInvalidEmptyPassword() {
        assertFalse(validator.isValid("", constraintValidatorContext));
    }
}
