package com.ProvaGrupo.SpringEcommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ProvaGrupo.SpringEcommerce.dto.CategoryDto;
import com.ProvaGrupo.SpringEcommerce.model.Category;
import com.ProvaGrupo.SpringEcommerce.repository.CategoryRepository;
import com.github.javafaker.Faker;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(SpringExtension.class)
public class CategoryServiceTest {
	
	@InjectMocks
	private CategoryService categoryService;
	
	@Mock
	private CategoryRepository categoryRepository;
	
	private Faker faker = new Faker();
	
	private Long existingId;
	private Long nonExistingId;
	private String name;
	private Category category;
	private List<Category> categories = new ArrayList<>();

	@BeforeEach
	void setUp() throws Exception{
		existingId = 1L;

		name = faker.commerce().productName();
		
		category = Category.builder()
				.name(name)
				.possibleFacets(List.of("facet1", "facet2"))
				.build();
	
		
		Mockito.when(categoryRepository.findAll()).thenReturn(categories);
		Mockito.when(categoryRepository.findById(existingId)).thenReturn(Optional.of(category));
		Mockito.when(categoryRepository.findById(nonExistingId)).thenReturn(Optional.empty());
		
		Mockito.when(categoryRepository.findByName(Mockito.eq(name))).thenReturn(List.of(category));
		Mockito.doNothing().when(categoryRepository).deleteById(existingId);
	    Mockito.doThrow(EntityNotFoundException.class).when(categoryRepository).deleteById(nonExistingId);

	}
	
	@Test
	public void findAllShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
	    Assertions.assertThrows(Exception.class, () -> {
	    	categoryService.findById(nonExistingId);
	    });
	    
	    Mockito.verify(categoryRepository, Mockito.times(1)).findById(nonExistingId);
	    
	}
	
	@Test
	public void findShouldDoNothingWhenIdExists() {
	    Assertions.assertDoesNotThrow(() -> {
	        categoryService.findById(existingId);
	    });
	    
	    Mockito.verify(categoryRepository, Mockito.times(1)).findById(existingId);
	    
	}
	
	@Test
	public void findAllShouldReturnList() {
		List<CategoryDto> result = categoryService.findAll();

		Assertions.assertNotNull(result);
		Mockito.verify(categoryRepository,  Mockito.times(1)).findAll();
		
	}
	
	@Test
	public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
	    Assertions.assertThrows(EntityNotFoundException.class, () -> {
	        categoryService.delete(nonExistingId);
	    });
	    
	}

	@Test
	public void deleteShouldDoNothingWhenIdExists() {
	    Assertions.assertDoesNotThrow(() -> {
	        categoryService.delete(existingId);
	    });

	    Mockito.verify(categoryRepository, Mockito.times(1)).deleteById(existingId);
	    
	}
	
	@Test
	public void findByNameShouldReturnList() {
		List<CategoryDto> result = categoryService.findByName(name);
		
		Assertions.assertNotNull(result);
		Mockito.verify(categoryRepository,  Mockito.times(1)).findByName(name);
		
	}
	
}
