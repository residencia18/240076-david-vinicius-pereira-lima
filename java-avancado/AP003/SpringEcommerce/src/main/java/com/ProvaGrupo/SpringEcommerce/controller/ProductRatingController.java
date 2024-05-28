package com.ProvaGrupo.SpringEcommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProvaGrupo.SpringEcommerce.dto.ProductRatingDto;
import com.ProvaGrupo.SpringEcommerce.service.ProductRatingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products/ratings")
@Tag(name = "Product Ratings", description = "Operations related to product ratings")
public class ProductRatingController {

	@Autowired
    private ProductRatingService productRatingService;

    @PostMapping("/submit/{sku}")
    @Operation(summary = "Submit a new product rating", description = "Submit a new rating for a product identified by SKU")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Successfully created the product rating"),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "404", description = "Product not found")
    })
    public ResponseEntity<?> postRating(@RequestBody @Valid ProductRatingDto productRatingDto, @PathVariable @Parameter(description = "SKU of the product to rate", required = true) String sku) {
    	return productRatingService.postProductRating(productRatingDto, sku);
    }

    @PutMapping("/edit/{id}")
    @Operation(summary = "Edit an existing product rating", description = "Edit the details of an existing product rating by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully edited the product rating"),
        @ApiResponse(responseCode = "404", description = "Product rating not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<?>  editRating(@RequestBody @Valid ProductRatingDto productRatingDto, @PathVariable @Parameter(description = "ID of the product rating to edit", required = true) Long id) {
    	return productRatingService.editProductRating(productRatingDto, id);
    }

    @DeleteMapping("/delete/{ratingId}")
    @Operation(summary = "Delete a product rating", description = "Delete a product rating by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully deleted the product rating"),
        @ApiResponse(responseCode = "404", description = "Product rating not found")
    })
    public ResponseEntity<?>  deleteRating(@PathVariable @Parameter(description = "ID of the product rating to delete", required = true) Long ratingId) {
    	return productRatingService.deleteProductRating(ratingId);
    }

    @GetMapping("/get/{sku}")
    @Operation(summary = "Get product ratings", description = "Retrieve all ratings for a product identified	 by SKU")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved the product ratings"),
        @ApiResponse(responseCode = "404", description = "Product not found")
    })
    public ResponseEntity<?> getRating(@PathVariable @Parameter(description = "SKU of the product to retrieve ratings for", required = true) String sku) {
        return productRatingService.getProductRating(sku);
    }
}