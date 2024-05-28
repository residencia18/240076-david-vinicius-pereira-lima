package com.ProvaGrupo.SpringEcommerce.service;

import java.math.BigDecimal;
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

import com.ProvaGrupo.SpringEcommerce.dto.ProductDto;
import com.ProvaGrupo.SpringEcommerce.model.Category;
import com.ProvaGrupo.SpringEcommerce.model.Product;
import com.ProvaGrupo.SpringEcommerce.repository.ProductRepository;
import com.github.javafaker.Faker;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(SpringExtension.class)
public class ProductServiceTest {
	@InjectMocks
	private ProductService productService;
	
	@Mock
	private ProductRepository productRepository;
	private Faker faker = new Faker();
	
	private Long existingId;
	private Long nonExistingId;
	private String name;
	private Category category;
	private Product product;
	private List<Product> products = new ArrayList<>();
	
	@BeforeEach
	void setUp() throws Exception{
		existingId = 1L;

		name = faker.commerce().productName();
		
		category = Category.builder()
				.name(name)
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
        
        Mockito.when(productRepository.findAll()).thenReturn(products);
        Mockito.when(productRepository.findById(existingId)).thenReturn(Optional.of(product));
        Mockito.when(productRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        Mockito.when(productRepository.findByName(Mockito.eq(name))).thenReturn(List.of(product));
        Mockito.doNothing().when(productRepository).deleteById(existingId);
        Mockito.doThrow(EntityNotFoundException.class).when(productRepository).deleteById(nonExistingId);
    
	}
	
	@Test
	public void findAllShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
	    Assertions.assertThrows(Exception.class, () -> {
	    	productService.findById(nonExistingId);
	    });
	    
	    Mockito.verify(productRepository, Mockito.times(1)).findById(nonExistingId);
	    
	}
	
	@Test
	public void findShouldDoNothingWhenIdExists() {
	    Assertions.assertDoesNotThrow(() -> {
	        productService.findById(existingId);
	    });
	    
	    Mockito.verify(productRepository, Mockito.times(1)).findById(existingId);
	    
	}
	
	@Test
	public void findAllShouldReturnList() {
		List<ProductDto> result = productService.findAll();

		Assertions.assertNotNull(result);
		Mockito.verify(productRepository,  Mockito.times(1)).findAll();
		
	}
	
	@Test
	public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
	    Assertions.assertThrows(EntityNotFoundException.class, () -> {
	        productService.delete(nonExistingId);
	    });
	    
	}

	@Test
	public void deleteShouldDoNothingWhenIdExists() {
	    Assertions.assertDoesNotThrow(() -> {
	        productService.delete(existingId);
	    });

	    Mockito.verify(productRepository, Mockito.times(1)).deleteById(existingId);
	    
	}
	
	@Test
	public void findByNameShouldReturnList() {
		List<ProductDto> result = productService.findByName(name);
		
		Assertions.assertNotNull(result);
		Mockito.verify(productRepository,  Mockito.times(1)).findByName(name);
		
	}
	
	
}
