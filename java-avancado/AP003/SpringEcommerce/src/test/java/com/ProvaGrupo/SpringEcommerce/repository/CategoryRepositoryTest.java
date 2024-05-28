package com.ProvaGrupo.SpringEcommerce.repository;

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
import com.github.javafaker.Faker;


@DataJpaTest
public class CategoryRepositoryTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryRepositoryTest.class);
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private TestEntityManager testEntityManager;
	
	private Faker faker = new Faker();
	
	private Long existingId;
	private String name;
	private Category category;

	@BeforeEach
	void setUp() throws Exception{
		existingId = 1L;

		name = faker.commerce().productName();

		category = Category.builder()
				.name(name)
				.possibleFacets(List.of("facet1", "facet2"))
				.build();
	}
	
   @Test
    public void deleteShouldRemoveObjectWhenIdExists() {
        categoryRepository.deleteById(existingId);

        Optional<Category> result = categoryRepository.findById(existingId);
        Assertions.assertFalse(result.isPresent());
        LOGGER.info("Test deleteShouldRemoveObjectWhenIdExists: Category with ID {} successfully deleted.", existingId);
    }

    @Test
    public void saveShouldPersistWithAutoIncrementWhenIdIsNull() {
        testEntityManager.persistFlushFind(category);

        Category categoryNew = category;
        categoryNew.setId(null);

        category = categoryRepository.save(categoryNew);
        Assertions.assertNotNull(categoryNew.getId());
        Assertions.assertEquals(existingId + 1, categoryNew.getId());
        LOGGER.info("Test saveShouldPersistWithAutoIncrementWhenIdIsNull: Category saved with new ID {}.", categoryNew.getId());
    }

    @Test
    public void searchByIdShouldReturnObjectWhenIdExists() {
        Optional<Category> result = Optional.ofNullable(categoryRepository.getReferenceById(existingId));
        Assertions.assertTrue(result.isPresent());

        Category category_ = result.get();
        Assertions.assertNotNull(category_.getId());
        Assertions.assertEquals(existingId, category_.getId());
        LOGGER.info("Test searchByIdShouldReturnObjectWhenIdExists: Category found with ID {}.", existingId);
    }

    @Test
    public void searchByNameShouldReturnObjectWhenNameExists() {
        testEntityManager.persistFlushFind(category);

        List<Category> result = categoryRepository.findByName(name);
        Assertions.assertFalse(result.isEmpty());

        Category category_ = result.get(0);
        Assertions.assertNotNull(category_.getId());
        Assertions.assertEquals(name, category_.getName());
        LOGGER.info("Test searchByNameShouldReturnObjectWhenNameExists: Category found with name '{}'.", name);
    }
}
