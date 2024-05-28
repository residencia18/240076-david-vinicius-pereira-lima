package com.ProvaGrupo.SpringEcommerce.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.javafaker.Faker;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;


public class CategoryTest {
    private static final Faker faker = new Faker(new Locale("pt-BR"));
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryTest.class);
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    
	private String name;
	private Category category;
    
	@BeforeEach
	void setUp() throws Exception{

		name = faker.commerce().productName();

		category = Category.builder()
				.name(name)
				.possibleFacets(new ArrayList<>())
				.build();
	}
	
    @Test
    public void shouldCreateCategorySuccessfully() {

        LOGGER.info("Test shouldCreateCategorySuccessfully: Creating category with name '{}'", name);
        
        assertNull(category.getId());
        assertEquals(name, category.getName());
        assertTrue(category.getPossibleFacets().isEmpty());
    }
    
    @Test
    public void shouldSetCategoryPropertiesSuccessfully() {
        String name = faker.commerce().department();
        List<String> facets = new ArrayList<>(); 

        category.setName(name);
        category.setPossibleFacets(facets);
        LOGGER.info("Test shouldSetCategoryPropertiesSuccessfully: Setting category properties to name '{}'", name);

        assertEquals(name, category.getName());
        assertTrue(category.getPossibleFacets().isEmpty());
    }
    
    @Test
    public void shouldFailValidationWhenNameIsNull() {
        category.setName(null);

        var violations = validator.validate(category);

        assertEquals(1, violations.size());
        ConstraintViolation<Category> violation = violations.iterator().next();
        LOGGER.info("Test shouldFailValidationWhenNameIsNull: Validating category with null name");

        assertEquals("Category name cannot be null or empty", violation.getMessage());
    }
    
    @Test
    public void shouldFailValidationWhenInvalidNameLength() {
        
        category.setName("La");

        var violations = validator.validate(category);

        assertEquals(1, violations.size());
        ConstraintViolation<Category> violation = violations.iterator().next();
        LOGGER.info("Test shouldFailValidationWhenNameIsNull: Validating category Name Length");

        assertEquals("Category name must be between 3 and 100 characters", violation.getMessage());
    }
}