package com.ProvaGrupo.SpringEcommerce.model;



import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a category of products in the e-commerce system.
 * Each category has a unique identifier, a name, and a list of possible facets.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Category name cannot be null or empty")
    @Size(min = 3, max = 100, message = "Category name must be between 3 and 100 characters")
    private String name;
    
    @ElementCollection
    private List<String> possibleFacets;
}
