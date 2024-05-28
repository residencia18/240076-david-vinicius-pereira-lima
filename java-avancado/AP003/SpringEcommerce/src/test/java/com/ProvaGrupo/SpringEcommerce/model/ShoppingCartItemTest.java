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
import java.util.ArrayList;
import java.util.Locale;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShoppingCartItemTest {
    private static  final Faker faker = new Faker(new Locale("en-US"));
    private static final Logger log = LoggerFactory.getLogger(ShoppingCartItemTest.class);
    private static Validator validator;

    private Product product;

    @BeforeAll
    public static void setUp(){
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        validator = vf.getValidator();
    }

    @Test
    public void testValidShoppingCartItem(){
        ShoppingCartItem item = new ShoppingCartItem(1L, faker.commerce().productName(), new BigDecimal(faker.random().nextDouble()).setScale(2,RoundingMode.HALF_UP), null, null);
        Set<ConstraintViolation<ShoppingCartItem>> violations = validator.validate(item);
        log.info("--- Running ValidShoppingCartItem ---\nItem: {}\n", item);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testEmptyName(){
        ShoppingCartItem item = new ShoppingCartItem(1L, "", new BigDecimal(faker.random().nextDouble()).setScale(2, RoundingMode.HALF_UP), null, null);
        Set<ConstraintViolation<ShoppingCartItem>> violations = validator.validate(item);
        log.info("--- Running EmptyName ---\nItem: {}", item);
        assertEquals(1, violations.size());
        log.info("--- Violation Empty Name: {}\n", violations.iterator().next().getMessage());
        assertEquals("Item name shouldn't be empty", violations.iterator().next().getMessage());
    }

    @Test
    public void testNegativeOrZeroPrice(){
        ShoppingCartItem item = new ShoppingCartItem(1L, faker.commerce().productName(), new BigDecimal(faker.random().nextDouble()).setScale(2, RoundingMode.HALF_UP).subtract(BigDecimal.ONE), null, null);
        Set<ConstraintViolation<ShoppingCartItem>> violations = validator.validate(item);
        log.info("--- Running NegativeOrZeroPrice ---\nItem: {}", item);
        assertEquals(1, violations.size());
        log.info("--- Violation Negative or Zero Price: {}\n", violations.iterator().next().getMessage());
        assertEquals("Price should be greater than zero", violations.iterator().next().getMessage());
    }

    @Test
    public void testNullPrice(){
        ShoppingCartItem item = new ShoppingCartItem(1L, faker.commerce().productName(), null, null, null);
        Set<ConstraintViolation<ShoppingCartItem>> violations = validator.validate(item);
        log.info("--- Running NullPrice ---\nItem: {}", item);
        assertEquals(1, violations.size());
        log.info("--- Violation Null Price: {}\n", violations.iterator().next().getMessage());
        assertEquals("Price shouldn't be null", violations.iterator().next().getMessage());
    }

}
