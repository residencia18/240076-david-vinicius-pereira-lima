package com.ProvaGrupo.SpringEcommerce.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.ProvaGrupo.SpringEcommerce.model.Category;
import com.ProvaGrupo.SpringEcommerce.model.Product;
import com.ProvaGrupo.SpringEcommerce.model.ProductRating;
import com.github.javafaker.Faker;

import lombok.extern.slf4j.Slf4j;

@DataJpaTest
@Slf4j
public class ProductRatingRepositoryTest {

    @Autowired
    private ProductRatingRepository productRatingRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    private ProductRating productRating;
    
    private Faker faker;
    
    private Product product;
    
    private Category category;

    @BeforeEach
    void setUp() throws Exception {
        log.info("Setting up test data");

        faker = new Faker();
        
        // Initialize and save the Category entity
        category = new Category();
        category.setName(faker.commerce().department());
        category = testEntityManager.persistAndFlush(category);
        
        // Initialize and save the Product entity
        product = new Product();
        product.setName(faker.commerce().productName());
        product.setDescription(faker.lorem().sentence());
        product.setPrice(BigDecimal.valueOf(faker.number().randomDouble(2, 1, 100)));
        product.setSku(faker.regexify("[A-Z0-9_-]{2,50}"));
        product.setImageUrl("https://s2-techtudo.glbimg.com/SSAPhiaAy_zLTOu3Tr3ZKu2H5vg=/0x0:1024x609/888x0/smart/filters:strip_icc()/i.s3.glbimg.com/v1/AUTH_08fbf48bc0524877943fe86e43087e7a/internal_photos/bs/2022/c/u/15eppqSmeTdHkoAKM0Uw/dall-e-2.jpg");
        product.setCategory(category);
        product.setQuantity(faker.number().numberBetween(1, 100));
        product.setManufacturer(faker.company().name());
        product.setFeatured(faker.bool().bool());
        product = testEntityManager.persistAndFlush(product);
        
        // Initialize the ProductRating entity and set its product to the saved product
        productRating = new ProductRating();
        productRating.setRatingStars(BigDecimal.valueOf(faker.number().randomDouble(1, 1, 5)));
        productRating.setProduct(product);
        productRating.setUserName(faker.regexify("[a-zA-Z0-9]{5,16}"));
    }

    @Test
    void saveProductRating_ShouldInsertAndReturnTheProductRating() {
        log.info("Testing saveProductRating: START");

        ProductRating savedProductRating = productRatingRepository.save(productRating);

        assertThat(savedProductRating).isNotNull();
        assertThat(savedProductRating.getId()).isNotNull();
        assertThat(savedProductRating.getRatingStars()).isEqualTo(productRating.getRatingStars());
        assertThat(savedProductRating.getProduct()).isEqualTo(productRating.getProduct());
        assertThat(savedProductRating.getReview()).isEqualTo(productRating.getReview());
        assertThat(savedProductRating.getUserName()).isEqualTo(productRating.getUserName());

        log.info("Testing saveProductRating: COMPLETED");
    }

    @Test
    void findByUserName_ReturnProductRatingIfUserNameExistsInDatabase() {
        log.info("Testing findByUserName: START");

        ProductRating savedProductRating = testEntityManager.persistFlushFind(productRating);

        Optional<ProductRating> foundProductRating = productRatingRepository.findByUserName(savedProductRating.getUserName());

        assertThat(foundProductRating).isPresent();
        assertThat(foundProductRating.get().getId()).isEqualTo(savedProductRating.getId());

        log.info("Testing findByUserName: COMPLETED");
    }

    @Test
    void findAll_ReturnAllProductRatings() {
        log.info("Testing findAll: START");

        productRatingRepository.save(productRating);

        List<ProductRating> allProductRatings = productRatingRepository.findAll();

        assertThat(allProductRatings.size()).isEqualTo(1);

        log.info("Testing findAll: COMPLETED");
    }

    @Test
    void delete_DeletesProductRatingFromDatabase() {
        log.info("Testing delete: START");

        ProductRating savedProductRating = testEntityManager.persistFlushFind(productRating);

        productRatingRepository.delete(savedProductRating);

        Optional<ProductRating> deletedProductRating = productRatingRepository.findById(savedProductRating.getId());
        assertTrue(deletedProductRating.isEmpty());

        log.info("Testing delete: COMPLETED");
    }
}
