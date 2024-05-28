package com.ProvaGrupo.SpringEcommerce.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ProvaGrupo.SpringEcommerce.controller.form.ProductForm;
import com.ProvaGrupo.SpringEcommerce.dto.ProductDto;
import com.ProvaGrupo.SpringEcommerce.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/product")
@Tag(name = "Product Controller", description = "Operations pertaining to products in the store")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@GetMapping("/get/")
	@Operation(summary = "View a list of available products", description = "Provides a list of all available products, optionally filtered by name")
	@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
        @ApiResponse(responseCode = "204", description = "No content found"),
        @ApiResponse(responseCode = "400", description = "Invalid parameters supplied")
    })
	public ResponseEntity<List<ProductDto>> findAll(@RequestParam(value = "name", required = false) String name) {
	    try {
	        List<ProductDto> list;
	        list = (name != null && !name.isEmpty()) ? service.findByName(name) : service.findAll();

	        if (list.isEmpty()) {
	            return ResponseEntity.noContent().build();
	        }
            log.info("Executing Product search operation.");
            return ResponseEntity.ok().body(list);
        } catch (IllegalArgumentException e) {
            log.error("Invalid search or sorting parameters: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
	}
	
	@PutMapping("/edit/{id}")
	@Operation(summary = "Update an existing product", description = "Updates the details of an existing product by its ID")
	@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully updated the product"),
        @ApiResponse(responseCode = "404", description = "Product not found")
    })
	public ResponseEntity<ProductDto> update(@PathVariable @Parameter(description = "ID of the product to be updated", required = true)Long id, @RequestBody ProductForm productForm) {
        try {
            ProductDto productDto = service.update(id, productForm);
            log.info("Updating product: {}", productForm.name());
            return ResponseEntity.ok().body(productDto);
        } catch (Exception e) {
            log.error("product not found with id: {}", id);
            return ResponseEntity.notFound().build();
        }
	}
	
	@GetMapping(value = "/get/{id}")
	@Operation(summary = "Get a product by ID", description = "Fetches the details of a product by its ID")
	@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved the product"),
        @ApiResponse(responseCode = "404", description = "Product not found")
    })
	public ResponseEntity<ProductDto> findById(@PathVariable @Parameter(description = "ID of the product to be fetched", required = true) Long id) {
        try {
            ProductDto productDto = service.findById(id);
            log.info("Executing product search operation by ID: {}", id);
            return ResponseEntity.ok().body(productDto);
        } catch (Exception e) {
            log.error("product not found with id: {}", id);
            return ResponseEntity.notFound().build();
        }
	}
	
	@PostMapping("/submit")
	@Operation(summary = "Add a new product", description = "Adds a new product to the store")
	@ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Successfully created the product"),
        @ApiResponse(responseCode = "404", description = "Error creating the product")
    })
	public ResponseEntity<ProductDto> insert(@RequestBody ProductForm productForm, UriComponentsBuilder uriC) {
		try {
			ProductDto productDto = service.insert(productForm);
			URI uri = uriC.path("/api/product/{id}").buildAndExpand(productDto.getId()).toUri();
			log.info("Inserting new product: {}", productDto.getId());
			return ResponseEntity.created(uri).body(productDto);
        } catch (Exception e) {
        	log.error("Error inserting produc: {}", e.getMessage());
            return ResponseEntity.notFound().build();
       }
	}
	
	@DeleteMapping("/delete/{id}")
	@Operation(summary = "Delete a product", description = "Deletes an existing product by its ID")
	@ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Successfully deleted the product"),
        @ApiResponse(responseCode = "404", description = "Product not found")
    })
	public ResponseEntity<Void> delete(@PathVariable @Parameter(description = "ID of the product to be deleted", required = true) Long id) {
		try {
			service.delete(id);
			log.info("product with ID {} was successfully deleted.", id);
			return ResponseEntity.noContent().build();
			
        } catch (Exception e) {
        	log.error("Category not found with id: {}", id);
            return ResponseEntity.notFound().build();
        }
	}
	
//    @DeleteMapping("/")
//    public ResponseEntity<Void> deleteCategoryNull() {
//        log.warn("Attempt to delete without specifying ID");
//        return ResponseEntity.badRequest().build();
//    }
//    
//    @PutMapping("/")
//    public ResponseEntity<Void> updateCategoryNull() {
//        log.warn("Attempt to update without specifying ID");
//        return ResponseEntity.badRequest().build();
//    }
}
