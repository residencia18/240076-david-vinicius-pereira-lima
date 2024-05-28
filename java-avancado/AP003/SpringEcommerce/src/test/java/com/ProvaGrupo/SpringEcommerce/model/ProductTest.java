package com.ProvaGrupo.SpringEcommerce.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.javafaker.Faker;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;


public class ProductTest {
	
    private static final Faker faker = new Faker(new Locale("pt-BR"));
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductTest.class);
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    
	private String name;
	private Product product;
	private Category category;
	
	@BeforeEach
    void setUp() throws Exception {

        name = faker.commerce().productName();

        category = Category.builder()
                .name("Electronics")
                .possibleFacets(new ArrayList<>())
                .build();
       
     
        product = Product.builder()
                .name(name)
                .description("Example product description")
                .price(new BigDecimal("99.99"))
                .sku(faker.regexify("[a-zA-Z0-9]{2,50}"))
                .imageUrl("https://example.com/image.jpg")
                .category(category)
                .productAttributeList(new ArrayList<>())
                .quantity(10)
                .manufacturer("Example Manufacturer")
                .featured(true)
                .productRating(new ArrayList<>())
                .build();
    }
	
	@Test
	public void shouldFailValidationWhenNameIsNullAndEmpty() {
	    product.setName(null);
	    Set<ConstraintViolation<Product>> violations = validator.validate(product);
	    assertTrue(violations.stream().anyMatch(violation -> violation.getMessage().equals("Product name is required")));


	    product.setName("");
	    Set<ConstraintViolation<Product>>  violationsEmpty = validator.validate(product);
	    assertTrue(violationsEmpty.stream().anyMatch(violation -> violation.getMessage().equals("Product name is required")));

	    LOGGER.info("Test shouldFailValidationWhenNameIsNullAndEmpty: Validating product with null and empty name");
	}
	
    @Test
    public void shouldFailValidationWhenNameIsSize() {
    	product.setName("Na");
    	Set<ConstraintViolation<Product>> violations = validator.validate(product);
	    assertTrue(violations.stream().anyMatch(violation -> violation.getMessage().equals("Product name needs to be at least 3 characters long and at most 100 characters long")));

        
        String name = "a".repeat(102);
       	product.setName(name);
    	Set<ConstraintViolation<Product>> violationsMax = validator.validate(product);
	    assertTrue(violationsMax.stream().anyMatch(violation -> violation.getMessage().equals("Product name needs to be at least 3 characters long and at most 100 characters long")));
       
	    LOGGER.info("Test shouldFailValidationWhenNameIsNull: Validating product  with name Length");

    }
    
    @Test
    public void shouldFailValidationWhenNameIsDescription() {
        String description = "a".repeat(1006);
       	product.setDescription(description);
    	Set<ConstraintViolation<Product>> violationsMax = validator.validate(product);
	    assertTrue(violationsMax.stream().anyMatch(violation -> violation.getMessage().equals("Description cannot be longer than 1000 characters")));
	    
        LOGGER.info("Test shouldFailValidationWhenNameIsNull: Validating product  with description Length");

    }
    
    @Test
    public void shouldFailValidationWhenSkuIsInvalid() {
        product.setSku("A");
        Set<ConstraintViolation<Product>> violationsShort = validator.validate(product);
        assertTrue(violationsShort.stream().anyMatch(violation -> violation.getMessage().equals("SKU must contain only uppercase letters, numbers, hyphens, or underscores, and be between 2 and 50 characters long")));


        String sku = "A".repeat(51);
        product.setSku(sku);
        Set<ConstraintViolation<Product>> violationsLong = validator.validate(product);
        assertTrue(violationsLong.stream().anyMatch(violation -> violation.getMessage().equals("SKU must contain only uppercase letters, numbers, hyphens, or underscores, and be between 2 and 50 characters long")));


        product.setSku("invalid#sku");
        Set<ConstraintViolation<Product>> violationsInvalid = validator.validate(product);
        assertTrue(violationsInvalid.stream().anyMatch(violation -> violation.getMessage().equals("SKU must contain only uppercase letters, numbers, hyphens, or underscores, and be between 2 and 50 characters long")));

        LOGGER.info("Test shouldFailValidationWhenSkuIsInvalid: Validating product with invalid sku");
    }
    
    

    @Test
    public void shouldFailValidationWhenCategoryIsNull() {
        product.setCategory(null);
        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        assertTrue(violations.stream().anyMatch(violation -> violation.getMessage().equals("Category is required")));

        LOGGER.info("Test shouldFailValidationWhenCategoryIsNull: Validating product with null category");
    }

    @Test
    public void shouldFailValidationWhenQuantityIsNegative() {
        product.setQuantity(-1);
        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        assertTrue(violations.stream().anyMatch(violation -> violation.getMessage().equals("Quantity cannot be negative")));

        LOGGER.info("Test shouldFailValidationWhenQuantityIsNegative: Validating product with negative quantity");
    }

    @Test
    public void shouldFailValidationWhenManufacturerIsTooLong() {
        product.setManufacturer("a".repeat(101));
        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        assertTrue(violations.stream().anyMatch(violation -> violation.getMessage().equals("Manufacturer name cannot be longer than 100 characters")));

        LOGGER.info("Test shouldFailValidationWhenManufacturerIsTooLong: Validating product with manufacturer name too long");
    }


	
}