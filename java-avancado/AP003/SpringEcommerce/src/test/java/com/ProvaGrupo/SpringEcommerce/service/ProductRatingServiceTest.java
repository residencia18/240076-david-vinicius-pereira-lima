package com.ProvaGrupo.SpringEcommerce.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ProvaGrupo.SpringEcommerce.dto.ProductRatingDto;
import com.ProvaGrupo.SpringEcommerce.model.Product;
import com.ProvaGrupo.SpringEcommerce.model.ProductRating;
import com.ProvaGrupo.SpringEcommerce.repository.ProductRatingRepository;
import com.ProvaGrupo.SpringEcommerce.repository.ProductRepository;
import com.github.javafaker.Faker;

@ExtendWith(MockitoExtension.class)
class ProductRatingServiceTest {

    @Mock
    private ProductRatingRepository productRatingRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductRatingService productRatingService;

    private Product product;
    private ProductRating productRating;
    private ProductRatingDto productRatingDto;
    private Faker faker;

    @BeforeEach
    void setUp() {
        faker = new Faker();

        product = new Product();
        product.setId(1L);
        product.setSku(faker.commerce().promotionCode());
        product.setName(faker.commerce().productName()); 
        product.setDescription(faker.lorem().sentence()); 
        product.setPrice(BigDecimal.valueOf(faker.number().randomDouble(2, 10, 100))); 
        product.setImageUrl("example.com/image.jpg"); 
        product.setQuantity(10); 
        product.setManufacturer("Example Manufacturer");
        product.setFeatured(true); 

        productRating = new ProductRating();
        productRating.setId(1L);
        productRating.setRatingStars(BigDecimal.valueOf(faker.number().numberBetween(1, 5)));
        productRating.setReview(faker.lorem().sentence());
        productRating.setUserName(faker.name().username());
        productRating.setProduct(product);

        productRatingDto = new ProductRatingDto(
            BigDecimal.valueOf(faker.number().numberBetween(1, 5)),
            faker.lorem().sentence(),
            faker.name().username()
        );
    }

    @Test
    void postProductRating_WithValid_ReturnsCreatedStatus() {
        when(productRepository.findBySku(product.getSku())).thenReturn(Optional.of(product));
        when(productRatingRepository.save(any(ProductRating.class))).thenReturn(productRating);

        ResponseEntity<?> response = productRatingService.postProductRating(productRatingDto, product.getSku());

        verify(productRepository).findBySku(product.getSku());
        verify(productRatingRepository).save(any(ProductRating.class));
        verifyNoMoreInteractions(productRepository, productRatingRepository);

        assertDoesNotThrow(() -> productRatingService.postProductRating(productRatingDto, product.getSku()));
    }

    @Test
    void postProductRating_WithInvalidSku_ThrowsException() {
        when(productRepository.findBySku("INVALID_SKU")).thenReturn(Optional.empty());

        ResponseEntity<?> response = productRatingService.postProductRating(productRatingDto, "INVALID_SKU");

        verify(productRepository).findBySku("INVALID_SKU");
        verifyNoMoreInteractions(productRepository, productRatingRepository);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error creating product rating", response.getBody());
    }

    @Test
    void getProductRating_WithExistingSku_ReturnsOkStatus() {
        when(productRepository.findBySku(product.getSku())).thenReturn(Optional.of(product));

        ResponseEntity<?> response = productRatingService.getProductRating(product.getSku());

        verify(productRepository).findBySku(product.getSku());
        verifyNoMoreInteractions(productRepository);

        assertDoesNotThrow(() -> productRatingService.getProductRating(product.getSku()));
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getProductRating_WithNonExistingSku_ThrowsException() {
        when(productRepository.findBySku("INVALID_SKU")).thenReturn(Optional.empty());

        ResponseEntity<?> response = productRatingService.getProductRating("INVALID_SKU");

        verify(productRepository).findBySku("INVALID_SKU");
        verifyNoMoreInteractions(productRepository);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void editProductRating_WithExistingId_ReturnsOkStatus() {
        when(productRatingRepository.findById(1L)).thenReturn(Optional.of(productRating));
        when(productRatingRepository.save(any(ProductRating.class))).thenReturn(productRating);

        ResponseEntity<?> response = productRatingService.editProductRating(productRatingDto, 1L);

        verify(productRatingRepository).findById(1L);
        verify(productRatingRepository).save(any(ProductRating.class));
        verifyNoMoreInteractions(productRatingRepository);

        assertDoesNotThrow(() -> productRatingService.editProductRating(productRatingDto, 1L));
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void editProductRating_WithNonExistingId_ThrowsException() {
        when(productRatingRepository.findById(2L)).thenReturn(Optional.empty());

        ResponseEntity<?> response = productRatingService.editProductRating(productRatingDto, 2L);

        verify(productRatingRepository).findById(2L);
        verifyNoMoreInteractions(productRatingRepository);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error editing product review", response.getBody());
    }

    @Test
    void deleteProductRating_WithExistingId_ReturnsOkStatus() {
        when(productRatingRepository.findById(1L)).thenReturn(Optional.of(productRating));

        ResponseEntity<?> response = productRatingService.deleteProductRating(1L);

        verify(productRatingRepository).findById(1L);
        verify(productRatingRepository).delete(productRating);
        verifyNoMoreInteractions(productRatingRepository);

        assertDoesNotThrow(() -> productRatingService.deleteProductRating(1L));
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deleteProductRating_WithNonExistingId_ThrowsException() {
        when(productRatingRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<?> response = productRatingService.deleteProductRating(1L);

        verify(productRatingRepository).findById(1L);
        verifyNoMoreInteractions(productRatingRepository);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error deleting product review", response.getBody());
    }
}
