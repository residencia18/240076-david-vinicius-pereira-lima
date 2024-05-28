package com.ProvaGrupo.SpringEcommerce.model;

import com.github.javafaker.Faker;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShoppingCartTest {
    private static final Faker faker = new Faker(new Locale("en-US"));
    private static final Logger log = LoggerFactory.getLogger(ShoppingCartItemTest.class);
    private static Validator validator;

    @BeforeAll
    public static void setUp(){
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        validator = vf.getValidator();
    }

    @Test
    public void testValidShoppingCart(){
        BigDecimal price = BigDecimal.valueOf(faker.random().nextDouble()).setScale(2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(faker.random().nextInt(9999)));
        ShoppingCart cart = new ShoppingCart(1L, price, faker.random().nextInt(999), faker.name().username(), null);
        Set<ConstraintViolation<ShoppingCart>> violations = validator.validate(cart);
        log.info("--- Running ValidShoppingCart ---\nCart: {}\n", cart);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testMinUsername(){
        BigDecimal price = BigDecimal.valueOf(faker.random().nextDouble()).setScale(2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(faker.random().nextInt(9999)));
        ShoppingCart cart = new ShoppingCart(1L, price, faker.random().nextInt(999), faker.name().username().substring(0, 2), null);
        Set<ConstraintViolation<ShoppingCart>> violations = validator.validate(cart);
        log.info("--- Running MinUsername ---\nCart: {}\n", cart);
        assertEquals(1, violations.size());
        log.info("--- Violation Min Username: {}\n", violations.iterator().next().getMessage());
        assertEquals("Username should be between 3 and 30 characters", violations.iterator().next().getMessage());
    }

    @Test
    public void testMaxUsername(){
        BigDecimal price = BigDecimal.valueOf(faker.random().nextDouble()).setScale(2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(faker.random().nextInt(9999)));
        ShoppingCart cart = new ShoppingCart(1L, price, faker.random().nextInt(999), faker.lorem().characters(31), null);
        Set<ConstraintViolation<ShoppingCart>> violations = validator.validate(cart);
        log.info("--- Running MaxUsername ---\nCart: {}\n", cart);
        assertEquals(1, violations.size());
        log.info("--- Violation Max Username: {}\n", violations.iterator().next().getMessage());
        assertEquals("Username should be between 3 and 30 characters", violations.iterator().next().getMessage());
    }

    @Test
    public void testNullCartTotalPrice(){
        ShoppingCart cart = new ShoppingCart(1L, null, faker.random().nextInt(999), faker.name().username(), null);
        Set<ConstraintViolation<ShoppingCart>> violations = validator.validate(cart);
        log.info("--- Running NullCartTotalPrice ---\nCart: {}\n", cart);
        assertEquals(1, violations.size());
        log.info("--- Violation Null Total Price: {}\n", violations.iterator().next().getMessage());
        assertEquals("Cart total price shouldn't be null", violations.iterator().next().getMessage());
    }

    @Test
    public void testNegativeCartTotalPrice(){
        BigDecimal price = BigDecimal.valueOf(faker.random().nextDouble()).setScale(2, RoundingMode.HALF_UP).subtract(BigDecimal.ONE).multiply(BigDecimal.valueOf(faker.random().nextInt(9999)));
        ShoppingCart cart = new ShoppingCart(1L, price, faker.random().nextInt(999), faker.name().username(), null);
        Set<ConstraintViolation<ShoppingCart>> violations = validator.validate(cart);
        log.info("--- Running NegativeCartTotalPrice ---\nCart: {}\n", cart);
        assertEquals(1, violations.size());
        log.info("--- Violation Negative Total Price: {}\n", violations.iterator().next().getMessage());
        assertEquals("Cart total price should to be 0 or greater", violations.iterator().next().getMessage());
    }

    @Test
    public void testMinNumberOfItems(){
        BigDecimal price = BigDecimal.valueOf(faker.random().nextDouble()).setScale(2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(faker.random().nextInt(9999)));
        ShoppingCart cart = new ShoppingCart(1L, price, faker.number().numberBetween(-999, 0), faker.name().username(), null);
        Set<ConstraintViolation<ShoppingCart>> violations = validator.validate(cart);
        log.info("--- Running MinNumberOfItems ---\nCart: {}\n", cart);
        assertEquals(1, violations.size());
        log.info("--- Violation Negative Number of Items: {}\n", violations.iterator().next().getMessage());
        assertEquals("Number of items should to be 0 or greater", violations.iterator().next().getMessage());
    }


}
