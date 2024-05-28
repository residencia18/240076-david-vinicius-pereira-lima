package com.ProvaGrupo.SpringEcommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ProvaGrupo.SpringEcommerce.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	List<Category> findByName(String name);
}