package com.ProvaGrupo.SpringEcommerce.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.ProvaGrupo.SpringEcommerce.model.Category;
import com.ProvaGrupo.SpringEcommerce.model.Product;
import com.github.javafaker.Faker;

@DataJpaTest
public class ProductRepositoryTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductRepositoryTest.class);
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private TestEntityManager testEntityManager;
	
	
	private Faker faker = new Faker();
	private Long existingId;
	private String name;
	private Product product;
	private Category category;
	
	@BeforeEach
    void setUp() throws Exception {
        existingId = 1L;

        name = faker.commerce().productName();

        category = Category.builder()
                .name("Electronics")
                .possibleFacets(new ArrayList<>())
                .build();
        testEntityManager.persistAndFlush(category);

     
        product = Product.builder()
                .name(name)
                .description("Example product description")
                .price(new BigDecimal("99.99"))
                .sku("ABC123")
                .imageUrl("https://example.com/image.jpg")
                .category(category)
                .quantity(10)
                .manufacturer("Example Manufacturer")
                .featured(true)
                .build();
    }
		
	 @Test
	 public void deleteShouldRemoveObjectWhenIdExists() {
	        testEntityManager.persistAndFlush(product);
	
	        productRepository.deleteById(existingId);
	
	        Optional<Product> result = productRepository.findById(existingId);
	        Assertions.assertFalse(result.isPresent());
	        LOGGER.info("Test deleteShouldRemoveObjectWhenIdExists: Product with ID {} successfully deleted.", existingId);
	    }

    @Test
    public void saveShouldPersistWithAutoIncrementWhenIdIsNull() {
        testEntityManager.persistFlushFind(product);

        Product productNew = product;
        productNew.setId(null);

        product = productRepository.save(productNew);
        Assertions.assertNotNull(productNew.getId());
        LOGGER.info("Test saveShouldPersistWithAutoIncrementWhenIdIsNull: Product saved with new ID {}.", productNew.getId());
    }

    @Test
    public void searchByIdShouldReturnObjectWhenIdExists() {

        Optional<Product> result =Optional.ofNullable( productRepository.getReferenceById(existingId));
        Assertions.assertTrue(result.isPresent());

        Product product_ = result.get();
        Assertions.assertNotNull(product_.getId());
        Assertions.assertEquals(existingId, product_.getId());
        LOGGER.info("Test searchByIdShouldReturnObjectWhenIdExists: Product found with ID {}.", existingId);
    }

    @Test
    public void searchByNameShouldReturnObjectWhenNameExists() {
        testEntityManager.persistFlushFind(product);

        List<Product> result = productRepository.findByName(name);
        Assertions.assertFalse(result.isEmpty());

        Product product_ = result.get(0);
        Assertions.assertNotNull(product_.getId());
        Assertions.assertEquals(name, product_.getName());
        LOGGER.info("Test searchByNameShouldReturnObjectWhenNameExists: Product found with name '{}'.", name);
    }
    
    @Test
    public void findAllShouldReturnAllProducts() {
        List<Product> productList = new ArrayList<>();
        productList.add(product);

        testEntityManager.persistAndFlush(product);

        List<Product> result = productRepository.findAll();
        Assertions.assertEquals(productList.size(), result.size());
        LOGGER.info("Test findAllShouldReturnAllProducts: Found {} products.", result.size());
    }

    @Test
    public void findByProductNameShouldReturnProductWhenNameExists() {
        testEntityManager.persistFlushFind(product);

        List<Product> result = productRepository.findByName(name);


        Product product_ = result.get(0);
        Assertions.assertNotNull(product_.getId());
        Assertions.assertEquals(name, product_.getName());
        LOGGER.info("Test findByProductNameShouldReturnProductWhenNameExists: Product found with name '{}'.", name);
    }

    @Test
    public void saveShouldPersistProduct() {
        Product savedProduct = productRepository.save(product);
        Assertions.assertNotNull(savedProduct.getId());
        LOGGER.info("Test saveShouldPersistProduct: Product saved with ID {}.", savedProduct.getId());
    }
	
	

}
