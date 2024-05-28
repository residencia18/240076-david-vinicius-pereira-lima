package com.ProvaGrupo.SpringEcommerce.auth.util.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * Custom annotation to validate password througt the PasswordConstraintValidator
 */
@Documented
@Constraint(validatedBy = PasswordConstraintValidator.class)
@Target({ ElementType.METHOD,
          ElementType.FIELD,
          ElementType.ANNOTATION_TYPE,
          ElementType.CONSTRUCTOR,
          ElementType.PARAMETER,
          ElementType.TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {
    String message() default "Invalid password";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}