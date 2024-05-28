package com.ProvaGrupo.SpringEcommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ProvaGrupo.SpringEcommerce.dto.ProductRatingDto;
import com.ProvaGrupo.SpringEcommerce.model.Product;
import com.ProvaGrupo.SpringEcommerce.model.ProductRating;
import com.ProvaGrupo.SpringEcommerce.repository.ProductRatingRepository;
import com.ProvaGrupo.SpringEcommerce.repository.ProductRepository;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductRatingService {


    @Autowired
    ProductRatingRepository productRatingRepository;

    @Autowired
    ProductRepository productRepository;

    public ResponseEntity<?> postProductRating(@Valid ProductRatingDto productRatingDto, String sku) {
        try {
            log.info("Attempting to post a new product rating for SKU: {}", sku);
            ProductRating productRating = productRatingDto.toProductRating(productRatingDto);

            Product product = productRepository.findBySku(sku)
                    .orElseThrow(() -> new IllegalArgumentException("Product not found with the provided SKU"));
            productRating.setProduct(product);

            ProductRating savedRating = productRatingRepository.save(productRating);

            product.getProductRating().add(savedRating);

            productRepository.save(product);

            log.info("Successfully posted a new product rating for SKU: {}", sku);
            return new ResponseEntity<>(savedRating, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error creating product rating for SKU: {}", sku, e);
            return new ResponseEntity<>("Error creating product rating", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getProductRating(String sku) {
        try {
            log.info("Attempting to retrieve product ratings for SKU: {}", sku);
            Product product = productRepository.findBySku(sku)
                    .orElseThrow(() -> new IllegalArgumentException("Product not found with the provided SKU"));

            log.info("Successfully retrieved product ratings for SKU: {}", sku);
            return new ResponseEntity<>(product.getProductRating(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error retrieving product ratings for SKU: {}", sku, e);
            return new ResponseEntity<>("Error retrieving product reviews", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> editProductRating(@Valid ProductRatingDto productRatingDto, Long id){
        try {
            log.info("Attempting to edit product rating with ID: {}", id);
            ProductRating existingRating = productRatingRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Product review not found"));

            existingRating.setRatingStars(productRatingDto.ratingStars());
            existingRating.setReview(productRatingDto.review());
            existingRating.setUserName(productRatingDto.userName());

            ProductRating updatedRating = productRatingRepository.save(existingRating);

            log.info("Successfully edited product rating with ID: {}", id);
            return new ResponseEntity<>(updatedRating, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            log.error("Error editing product rating with ID: {}", id, e);
            return new ResponseEntity<>("Error editing product review", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> deleteProductRating(Long ratingId) {
        try {
            log.info("Attempting to delete product rating with ID: {}", ratingId);
            ProductRating productRating = productRatingRepository.findById(ratingId)
                    .orElseThrow(() -> new IllegalArgumentException("Product review not found"));
            productRatingRepository.delete(productRating);

            log.info("Successfully deleted product rating with ID: {}", ratingId);
            return new ResponseEntity<>("Product review successfully deleted", HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error deleting product rating with ID: {}", ratingId, e);
            return new ResponseEntity<>("Error deleting product review", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
