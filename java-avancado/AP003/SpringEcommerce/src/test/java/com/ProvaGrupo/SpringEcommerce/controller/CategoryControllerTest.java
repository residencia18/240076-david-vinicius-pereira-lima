package com.ProvaGrupo.SpringEcommerce.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.ProvaGrupo.SpringEcommerce.dto.CategoryDto;
import com.ProvaGrupo.SpringEcommerce.model.Category;
import com.ProvaGrupo.SpringEcommerce.model.CategoryTest;
import com.ProvaGrupo.SpringEcommerce.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import jakarta.persistence.EntityNotFoundException;


@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {
    private static final Faker faker = new Faker(new Locale("pt-BR"));
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryTest.class);

    
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CategoryService service;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	
	private String name;
	private Category category;
	private CategoryDto categoryDto;
	private Long existingId;
	private Long nonExistingId;
	private List<CategoryDto> categories = new ArrayList<>();
	
	@BeforeEach
	void setUp() throws Exception{
		existingId = 1L;
		nonExistingId = 2L;
		name = faker.commerce().productName();

		category = Category.builder()
				.name(name)
				.possibleFacets(new ArrayList<>())
				.build();
		CategoryDto categoryDto  = new CategoryDto(category);
		
		Mockito.when(service.findAll()).thenReturn(categories);
		Mockito.when(service.findById(existingId)).thenReturn(categoryDto);
		Mockito.when(service.findById(nonExistingId)).thenThrow(EntityNotFoundException.class);
		
		
		Mockito.when(service.update(Mockito.eq(existingId), Mockito.any())).thenReturn(categoryDto);
		Mockito.when(service.update(Mockito.eq(nonExistingId), Mockito.any())).thenThrow(EntityNotFoundException.class);
		
		Mockito.doNothing().when(service).delete(existingId);
		
		Mockito.when(service.insert(Mockito.any())).thenReturn(categoryDto);
	}
	
	
	@Test
	public void deleteShouldReturnNoContentWhenIdExists() throws Exception {
		
		ResultActions result = 
				mockMvc.perform(delete("/category/{id}", existingId))
						 .andExpect(status().isOk());
		
	}
	
}
