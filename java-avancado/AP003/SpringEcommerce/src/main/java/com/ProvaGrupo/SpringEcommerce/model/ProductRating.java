package com.ProvaGrupo.SpringEcommerce.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a rating given by a user to a product.
 * Each product rating has a unique identifier, rating stars, product ID, review (optional), and user name.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class ProductRating {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotNull(message = "Rating is required")
    @Min(value = 1, message = "Product rating must be between 1 and 5")
    @Max(value = 5, message = "Product rating must be between 1 and 5")
    private BigDecimal ratingStars;
    
    private String review;
    
    @NotBlank(message = "User name is required")
    @Size(min = 5, max = 16, message = "User name needs to be at least 5 characters long and at most 16 characters long")
    private String userName;

    
    @NotNull(message = "Product is required")
    @ManyToOne
    @JoinColumn(name = "product_id_class")
    private Product product;
    
}