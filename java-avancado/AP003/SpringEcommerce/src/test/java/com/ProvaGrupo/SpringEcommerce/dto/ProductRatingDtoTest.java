package com.ProvaGrupo.SpringEcommerce.dto;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ProvaGrupo.SpringEcommerce.model.Product;
import com.ProvaGrupo.SpringEcommerce.model.ProductRating;
import com.github.javafaker.Faker;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class ProductRatingDtoTest {

    private Validator validator;
    private ProductRating productRating;
    private Faker faker;
    private Product product;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        faker = new Faker();
        product = new Product();
        
        productRating = new ProductRating();
        productRating.setId(1L);
        productRating.setRatingStars(new BigDecimal(faker.number().randomDouble(1, 1, 5)));
        productRating.setProduct(product);
        productRating.setUserName(faker.regexify("[a-zA-Z0-9]{5,16}"));
    }
    
    @Test
    public void testProductRating_CreationWithNotViolations() {
        Set<ConstraintViolation<ProductRating>> violations = validator.validate(productRating);

        assertTrue(violations.isEmpty());
        log.info("No validation violations found for product rating creation.");
    }

    @Test
    public void testRatingStars_CreationWithMinMaxViolations() {
        productRating.setRatingStars(BigDecimal.valueOf(6));

        Set<ConstraintViolation<ProductRating>> violations = validator.validate(productRating);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Product rating must be between 1 and 5")));

        productRating.setRatingStars(BigDecimal.valueOf(0));

        violations = validator.validate(productRating);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Product rating must be between 1 and 5")));

        log.info("Rating stars validation tests completed.");
    }

    @Test
    public void testProductId_CreationWithNotNullViolations() {
        productRating.setProduct(null);

        Set<ConstraintViolation<ProductRating>> violations = validator.validate(productRating);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Product is required")));

        log.info("Product validation tests completed.");
    }

    @Test
    public void testUserName_CreationWithNotBlankViolations_ReturnUserNameIsRequired() {
        productRating.setUserName("");

        Set<ConstraintViolation<ProductRating>> violationsEmpty = validator.validate(productRating);

        assertFalse(violationsEmpty.isEmpty());
        assertTrue(violationsEmpty.stream().anyMatch(v -> v.getMessage().contains("User name is required")));
        

        productRating.setUserName(null);

        Set<ConstraintViolation<ProductRating>> violationsNull = validator.validate(productRating);

        assertFalse(violationsNull.isEmpty());
        assertTrue(violationsNull.stream().anyMatch(v -> v.getMessage().contains("User name is required")));

        log.info("User name validation tests completed.");
    }
    
    @Test
    public void testUserName_CreationWithSizeViolations_ReturnSizeViolation() {
    	productRating.setUserName("Mi12");

		Set<ConstraintViolation<ProductRating>> violationsMin = validator.validate(productRating);
		assertFalse(violationsMin.isEmpty());
		assertTrue(violationsMin.stream().anyMatch(v -> v.getMessage()
				.contains("User name needs to be at least 5 characters long and at most 16 characters long")));

		productRating.setUserName("TesteMaxName12345");

		Set<ConstraintViolation<ProductRating>> violationsMax = validator.validate(productRating);
		assertFalse(violationsMax.isEmpty());
		assertTrue(violationsMax.stream().anyMatch(v -> v.getMessage()
				.contains("User name needs to be at least 5 characters long and at most 16 characters long")));

        log.info("User name validation tests completed.");
    }

}
