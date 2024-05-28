package com.ProvaGrupo.SpringEcommerce.model;

import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;

public class ProductAttributeTest {
	
    private static final Faker faker = new Faker(new Locale("pt-BR"));
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
    public void testDefaultConstructor() {
        ProductAttribute productAttribute = new ProductAttribute();
        assertNull(productAttribute.getAttributeName());
        assertNull(productAttribute.getAttributeValue());
    }

    @Test
    public void testParameterizedConstructor() {
        ProductAttribute productAttribute = new ProductAttribute(1L, "Color", "Red", product);
        assertEquals("Color", productAttribute.getAttributeName());
        assertEquals("Red", productAttribute.getAttributeValue());
    }

    @Test
    public void testSetters() {
        ProductAttribute productAttribute = new ProductAttribute();
        productAttribute.setAttributeName("Size");
        productAttribute.setAttributeValue("Large");
        assertEquals("Size", productAttribute.getAttributeName());
        assertEquals("Large", productAttribute.getAttributeValue());
    }

    @Test
    public void testGetters() {
        ProductAttribute productAttribute = new ProductAttribute(1L, "Material", "Cotton", product);
        assertEquals("Material", productAttribute.getAttributeName());
        assertEquals("Cotton", productAttribute.getAttributeValue());
    }
}