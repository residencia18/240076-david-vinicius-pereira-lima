package com.ProvaGrupo.SpringEcommerce.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class to represent a product attribute object with the necessary fields. 
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class ProductAttribute implements Serializable {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Attribute name is required")
    private String attributeName;

    @NotBlank(message = "Attribute value is required")
    private String attributeValue;
    
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
