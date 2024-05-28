package com.ProvaGrupo.SpringEcommerce.dto;

import java.math.BigDecimal;

import com.ProvaGrupo.SpringEcommerce.model.ProductRating;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * DTO for transferring ProductRating data.
 */

public record ProductRatingDto(
    @NotNull(message = "Rating is required")
    @Min(value = 1, message = "Product rating must be between 1 and 5")
    @Max(value = 5, message = "Product rating must be between 1 and 5")
    BigDecimal ratingStars,

    String review,

    @NotBlank(message = "User name is required")
    @Size(min = 5, max = 16, message = "User name needs to be at least 5 characters long and at most 16 characters long")
    String userName
) {
	
	public ProductRating toProductRating(ProductRatingDto productRatingDto) {
        ProductRating productRating = new ProductRating();
        productRating.setRatingStars(productRatingDto.ratingStars());
        productRating.setReview(productRatingDto.review());
        productRating.setUserName(productRatingDto.userName());
        return productRating;
    }
	
    public ProductRatingDto(ProductRating productRating) {
        this(
            productRating.getRatingStars(),
            productRating.getReview(),
            productRating.getUserName()
        );
    }
}