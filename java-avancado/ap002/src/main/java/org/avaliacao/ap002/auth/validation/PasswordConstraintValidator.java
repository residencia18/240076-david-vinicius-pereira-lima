package org.avaliacao.ap002.auth.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {
    private List<String> commonPasswords;

    @Override
    public void initialize(ValidPassword constraintAnnotation) {
        try {
            this.commonPasswords = Files.lines(Paths.get("src/main/resources/10k-most-common.txt"))
                                      .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            this.commonPasswords = new ArrayList<>();
        }
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return value.length() >= 8 &&
                value.matches(".*[A-Z].*") &&
                value.matches(".*[a-z].*") &&
                value.matches(".*[0-9].*") &&
                value.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*") &&
                !commonPasswords.contains(value);
    }
}
