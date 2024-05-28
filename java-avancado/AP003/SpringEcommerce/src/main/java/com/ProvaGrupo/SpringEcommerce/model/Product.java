package com.ProvaGrupo.SpringEcommerce.model;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity representing a Product in the ecommerce application.
 * Includes validation constraints to ensure data integrity.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Product  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product name is required")
    @Size(min = 3, max = 100, message = "Product name needs to be at least 3 characters long and at most 100 characters long")
    private String name;

    @Size(max = 1000, message = "Description cannot be longer than 1000 characters")
    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
    @Digits(integer = 10, fraction = 2, message = "Price must be a valid monetary amount")
    private BigDecimal price;

    @NotBlank(message = "SKU is required")
    @Pattern(regexp = "^[A-Z0-9_-]{2,50}$", message = "SKU must contain only uppercase letters, numbers, hyphens, or underscores, and be between 2 and 50 characters long")
    private String sku;

    @URL(message = "Image URL must be a valid URL")
    @Size(max = 1000)
    private String imageUrl;

    @NotNull(message = "Category is required")
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

//    @NotNull(message = "Category ID is required")
//    @Column(name = "category_id")
//    private Long categoryId;
//    

    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductAttribute> productAttributeList;

    @Min(value = 0, message = "Quantity cannot be negative")
    private Integer quantity;

    @Size(max = 100, message = "Manufacturer name cannot be longer than 100 characters")
    private String manufacturer;

    private boolean featured;
    
    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductRating> productRating;

	public Product( String name, String description, BigDecimal price, String sku, String imageUrl, Category category, List<ProductAttribute> productAttributeList, Integer quantity, String manufacturer,
			boolean featured, List<ProductRating> productRating) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.sku = sku;
		this.imageUrl = imageUrl;
		this.category = category;
		this.productAttributeList = productAttributeList;
		this.quantity = quantity;
		this.manufacturer = manufacturer;
		this.featured = featured;
		this.productRating = productRating;
	}
	
    public boolean getFeatured() {
        return featured;
    }
}
