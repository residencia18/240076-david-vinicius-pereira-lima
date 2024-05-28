package com.ProvaGrupo.SpringEcommerce.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ProvaGrupo.SpringEcommerce.dto.ProductDto;
import com.ProvaGrupo.SpringEcommerce.model.Category;
import com.ProvaGrupo.SpringEcommerce.model.Product;
import com.ProvaGrupo.SpringEcommerce.model.ProductAttribute;
import com.ProvaGrupo.SpringEcommerce.model.ProductRating;
import com.ProvaGrupo.SpringEcommerce.repository.CategoryRepository;
import com.ProvaGrupo.SpringEcommerce.repository.ProductRatingRepository;
import com.ProvaGrupo.SpringEcommerce.repository.ProductRepository;

import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityNotFoundException;
import com.ProvaGrupo.SpringEcommerce.controller.form.ProductForm;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private ProductRatingRepository productRatingRepository;
    

    @Transactional(readOnly = true)
    public List<ProductDto> findAll() {
        return repository.findAll().stream()
                .map(this::convertToProductDto)
                .collect(Collectors.toList());
    }

    private ProductDto convertToProductDto(Product product) {
        Set<ProductRating> productRatings = productRatingRepository.findByProductId(product.getId()).orElse(Set.of());
        return new ProductDto(product, productRatings);
    }
    
    @Transactional(readOnly = true)
    public List<ProductDto>findByName(String name) {
        return repository.findByName(name).stream()
                .map(ProductDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProductDto findById(Long id) {
        return repository.findById(id)
                .map(ProductDto::new)
                .orElseThrow(() -> new EntityNotFoundException("Product not found for id: " + id));
    }

    @Transactional
    public void delete(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found for id: " + id));
        repository.deleteById(id);
    }

    @Transactional
    public ProductDto update(Long id, ProductForm productForm) {
        Product entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found for id: " + id));

	    entity.setName(productForm.name());
	    entity.setDescription(productForm.description());
	    entity.setPrice(productForm.price());
	    entity.setSku(productForm.sku());
	    entity.setImageUrl(productForm.imageUrl());
        Optional<Category> optionalCategory = categoryRepository.findById(productForm.categoryId());
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            entity.setCategory(category);
        } else {
            LOGGER.error("Invalid category ID: {}",productForm.categoryId());
            throw new IllegalArgumentException("Invalid category ID: " + productForm.categoryId());
        }

	    List<ProductAttribute> newProductAttributeList = productForm.productAttributeList();
	    
	    entity.getProductAttributeList().clear();
	    entity.getProductAttributeList().addAll(newProductAttributeList);
	    entity.setQuantity(productForm.quantity());
	    entity.setManufacturer(productForm.manufacturer());
	    entity.setFeatured(productForm.featured());
	    
	    List<ProductRating> newProductRating = productForm.productRating();
	    entity.getProductRating().clear();
	    entity.getProductRating().addAll(newProductRating);

        entity = repository.save(entity);
        return new ProductDto(entity);
    }

    @Transactional
    public ProductDto insert(ProductForm productForm) {
        Product entity = new Product();
	    entity.setName(productForm.name());
	    entity.setDescription(productForm.description());
	    entity.setPrice(productForm.price());
	    entity.setSku(productForm.sku());
	    entity.setImageUrl(productForm.imageUrl());

	    Optional<Category> optionalCategory = categoryRepository.findById(productForm.categoryId());
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            entity.setCategory(category);
        } else {
            LOGGER.error("Invalid category ID: {}",productForm.categoryId());
            throw new IllegalArgumentException("Invalid category ID: " + productForm.categoryId());
        }


	    List<ProductAttribute> newProductAttributeList = productForm.productAttributeList();
	    entity.setProductAttributeList(newProductAttributeList);

	    entity.setQuantity(productForm.quantity());
	    entity.setManufacturer(productForm.manufacturer());
	    entity.setFeatured(productForm.featured());

	    List<ProductRating> newProductRating = productForm.productRating();
	    entity.setProductRating(newProductRating);

        entity = repository.save(entity);
        return new ProductDto(entity);
    }
}
