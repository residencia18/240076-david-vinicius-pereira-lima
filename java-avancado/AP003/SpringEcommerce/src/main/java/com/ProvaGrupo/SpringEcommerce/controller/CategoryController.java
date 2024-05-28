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

import com.ProvaGrupo.SpringEcommerce.controller.form.CategoryForm;
import com.ProvaGrupo.SpringEcommerce.dto.CategoryDto;
import com.ProvaGrupo.SpringEcommerce.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/api/category")
@Tag(name = "Category", description = "Operations related to product categories")
public class CategoryController {
	
	@Autowired
    private CategoryService service;

    @GetMapping("/get/")
    @Operation(summary = "Retrieve all categories", description = "Retrieve a list of all categories or filter by name")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of categories"),
        @ApiResponse(responseCode = "204", description = "No categories found"),
        @ApiResponse(responseCode = "400", description = "Invalid search parameters")
    })
    public ResponseEntity<List<CategoryDto>> findAll(@RequestParam(value = "name", required = false) String name) {
        try {
            List<CategoryDto> list;
            list = (name != null && !name.isEmpty()) ? service.findByName(name) : service.findAll();

            if (list.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            log.info("Executing category search operation.");
            return ResponseEntity.ok().body(list);
        } catch (IllegalArgumentException e) {
            log.error("Invalid search or sorting parameters: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/edit/{id}")
    @Operation(summary = "Update a category", description = "Update the details of an existing category by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully updated the category"),
        @ApiResponse(responseCode = "404", description = "Category not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<CategoryDto> update(
            @PathVariable @Parameter(description = "ID of the category to update", required = true) Long id,
            @RequestBody @Parameter(description = "Updated category details", required = true) CategoryForm categoryFor) {
        try {
            CategoryDto categoryDto = service.update(id, categoryFor);
            log.info("Updating category: {}", categoryFor.name());
            return ResponseEntity.ok().body(categoryDto);
        } catch (Exception e) {
            log.error("Category not found with id: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "Retrieve a category by ID", description = "Retrieve the details of a category by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved the category"),
        @ApiResponse(responseCode = "404", description = "Category not found")
    })
    public ResponseEntity<CategoryDto> findById(
            @PathVariable @Parameter(description = "ID of the category to retrieve", required = true) Long id) {
        try {
            CategoryDto categoryDto = service.findById(id);
            log.info("Executing category search operation by ID: {}", id);
            return ResponseEntity.ok().body(categoryDto);
        } catch (Exception e) {
            log.error("Category not found with id: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/submit")
    @Operation(summary = "Create a new category", description = "Create a new category with the provided details")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Successfully created the category"),
        @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<CategoryDto> insert(
            @RequestBody @Parameter(description = "Details of the category to create", required = true) CategoryForm categoryFor,
            UriComponentsBuilder uriC) {
        try {
            CategoryDto categoryDto = service.insert(categoryFor);
            URI uri = uriC.path("/api/category/{id}").buildAndExpand(categoryDto.getId()).toUri();
            log.info("Inserting new category: {}", categoryFor.name());
            return ResponseEntity.created(uri).body(categoryDto);
        } catch (Exception e) {
            log.error("Error inserting category: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a category", description = "Delete a category by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Successfully deleted the category"),
        @ApiResponse(responseCode = "404", description = "Category not found")
    })
    public ResponseEntity<Void> delete(
            @PathVariable @Parameter(description = "ID of the category to delete", required = true) Long id) {
        try {
            service.delete(id);
            log.info("Category with ID {} was successfully deleted.", id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("Category not found with id: {}", id);
            return ResponseEntity.notFound().build();
        }
    }
	
//    @DeleteMapping("/delete/")
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
