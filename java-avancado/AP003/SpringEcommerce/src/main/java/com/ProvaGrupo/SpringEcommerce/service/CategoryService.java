package com.ProvaGrupo.SpringEcommerce.service;

import com.ProvaGrupo.SpringEcommerce.controller.form.CategoryForm;
import com.ProvaGrupo.SpringEcommerce.dto.CategoryDto;
import com.ProvaGrupo.SpringEcommerce.model.*;
import java.util.List;

import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ProvaGrupo.SpringEcommerce.repository.CategoryRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
	
	
    @Transactional(readOnly = true)
    public List<CategoryDto> findAll() {
        return repository.findAll().stream()
                         .map(CategoryDto::new)
                         .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
	public List<CategoryDto>findByName(String name) {
	    return repository.findByName(name).stream()
				                .map(CategoryDto::new)
				                .collect(Collectors.toList());
	}
	
    @Transactional(readOnly = true)
	public CategoryDto findById(Long id) {
		return repository.findById(id)
		         .map(CategoryDto::new)
		         .orElseThrow(() -> new EntityNotFoundException("Category not found for id: " + id));
	}
	

    @Transactional
    public void delete(Long id) {
        repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Category not found for id: " + id));        
        repository.deleteById(id);
    }
    
    @Transactional
    public CategoryDto update(Long id, CategoryForm categoryForm) {
        Category entity = repository.findById(id)
        						.orElseThrow(() -> new EntityNotFoundException("User not found for id: " + id));
        entity.setName(categoryForm.name());
        List<String> newFacets = categoryForm.possibleFacets();
        entity.getPossibleFacets().clear();
        entity.getPossibleFacets().addAll(newFacets);
        entity = repository.save(entity);
        return new CategoryDto(entity);
    }
    
    @Transactional
	public CategoryDto insert(CategoryForm categoryForm) {
		Category entity = new Category();
	    System.out.println("Possible Facets: " + categoryForm.possibleFacets()); // Verifique o valor aqui

		entity.setName(categoryForm.name());
		entity.setPossibleFacets(categoryForm.possibleFacets());
		entity = repository.save(entity);
		return new CategoryDto(entity);
	}

}
