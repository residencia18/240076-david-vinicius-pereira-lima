package com.ProvaGrupo.SpringEcommerce.repository;

import com.ProvaGrupo.SpringEcommerce.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<ShoppingCart , Long> {
    Optional <ShoppingCart> findByUsername(String username);
}
