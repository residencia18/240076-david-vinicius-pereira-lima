package com.ProvaGrupo.SpringEcommerce.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.ProvaGrupo.SpringEcommerce.auth.infra.security.TokenService;
import com.ProvaGrupo.SpringEcommerce.auth.repository.UserRepository;
import com.ProvaGrupo.SpringEcommerce.dto.ProductRatingDto;
import com.ProvaGrupo.SpringEcommerce.model.Product;
import com.ProvaGrupo.SpringEcommerce.model.ProductRating;
import com.ProvaGrupo.SpringEcommerce.service.ProductRatingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

@WebMvcTest(ProductRatingController.class)
class ProductRatingControllerTest {

	@MockBean
	private ProductRatingService productRatingService;
	
    @MockBean
    private TokenService tokenService;
    
    @MockBean
    private UserRepository userRepository;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private Faker faker;
	

	@TestConfiguration
	static class FakerTestConfig {

		@Bean
		public Faker faker() {
			return new Faker(new Locale("pt-BR"));
		}

		@Bean
	    public UserRepository userRepository() {
	        return mock(UserRepository.class);
	    }
		
	}

	private Product geradorProductFaker() {
		Product product = new Product();
		product.setId(1L);
		product.setSku(faker.commerce().promotionCode());
		product.setName(faker.commerce().productName());
		product.setDescription(faker.lorem().sentence());
		product.setPrice(BigDecimal.valueOf(faker.number().randomDouble(2, 10, 100)));
		product.setImageUrl("example.com/image.jpg");
		product.setQuantity(10);
		product.setManufacturer("Example Manufacturer");
		product.setFeatured(true);

		return product;
	}

	private ProductRatingDto geradorProductRatingDtoFaker() {
		ProductRatingDto productRatingDto = new ProductRatingDto(BigDecimal.valueOf(faker.number().numberBetween(1, 5)),
				faker.lorem().sentence(), faker.name().username());

		return productRatingDto;
	}

	@Test
	void postRating_deveSalvarPostagemEretornarCreated() throws Exception {
		ProductRatingDto productRatingDto = geradorProductRatingDtoFaker();
		Product product = geradorProductFaker();
		ProductRating productRating = productRatingDto.toProductRating(productRatingDto);
		productRating.setId(1L);
		productRating.setProduct(product);
		
        when(tokenService.validateToken(any(String.class))).thenReturn("username");

        when(productRatingService.postProductRating(any(ProductRatingDto.class), any(String.class)))
        .thenReturn(new ResponseEntity<>(HttpStatus.CREATED));

		mockMvc.perform(post("/submit/" + product.getSku()).content(objectMapper.writeValueAsString(productRatingDto))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
				.andExpect(content().json(objectMapper.writeValueAsString(productRating)));
	}

}
