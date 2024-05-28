package com.ProvaGrupo.SpringEcommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ProvaGrupo.SpringEcommerce.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	List<Product>findByName(String name);

    Optional<Product> findBySku(String sku);

}
