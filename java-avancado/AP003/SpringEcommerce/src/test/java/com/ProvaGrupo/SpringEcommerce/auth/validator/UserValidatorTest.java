package com.ProvaGrupo.SpringEcommerce.auth.validator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ProvaGrupo.SpringEcommerce.auth.model.User;
import com.ProvaGrupo.SpringEcommerce.auth.model.UserRole;
import com.github.javafaker.Faker;

import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class UserValidatorTest {

    private Validator validator;

    private Faker faker = new Faker();

    String validUsername = "validUsername";
    String validPassword = faker.lorem().characters(10, 15) + "A1#";
    String validEmail = faker.internet().emailAddress();
    LocalDate validBirthDate = LocalDate.now().minusYears(20);
    String validMobilePhone = faker.phoneNumber().cellPhone();

    String invalidUsername = "abc";
    String invalidPassword = faker.lorem().characters(10, 15); // no uppercase, no number, no special character
    String invalidEmail = "invalidEmail";
    LocalDate invalidBirthDate = LocalDate.now().plusDays(1); // future date
    String invalidMobilePhone = "3620"; // only 4 digits

    @Mock
    private ConstraintValidatorContext constraintValidatorContext;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        validUsername = "validUsername";
        validPassword = faker.lorem().characters(10, 15) + "A1#";
        validEmail = faker.internet().emailAddress();
        validBirthDate = LocalDate.now().minusYears(20);
        validMobilePhone = faker.number().digits(11);

        invalidUsername = "abc";
        invalidPassword = faker.lorem().characters(10, 15); // no uppercase, no number, no special character
        invalidEmail = "invalidEmail";
        invalidBirthDate = LocalDate.now().plusDays(1); // future date
        invalidMobilePhone = "3620"; // only 4 digits
    }

    @Test
    public void testValidUser() {
        User user = User.builder()
                .username(validUsername)
                .password(validPassword)
                .email(validEmail)
                .role(UserRole.USER)
                .build();

        assertTrue(validator.validate(user).isEmpty());
    }

    @Test
    public void testInvalidUsername() {
        User user = User.builder()
                .username(invalidUsername)
                .password(validPassword)
                .email(validEmail)
                .role(UserRole.USER)
                .build();

        assertFalse(validator.validate(user).isEmpty());
    }

    @Test
    public void testInvalidEmail() {
        User user = User.builder()
                .username(validUsername)
                .password(validPassword)
                .email(invalidEmail)
                .role(UserRole.USER)
                .build();

        assertFalse(validator.validate(user).isEmpty());
    }

}