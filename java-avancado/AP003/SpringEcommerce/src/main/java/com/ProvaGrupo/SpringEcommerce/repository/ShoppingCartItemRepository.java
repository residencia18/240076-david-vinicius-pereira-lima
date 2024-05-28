package com.ProvaGrupo.SpringEcommerce.repository;

import com.ProvaGrupo.SpringEcommerce.model.ShoppingCartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Long> {
    List<ShoppingCartItem> findByShoppingCartId(Long id);
    Optional<ShoppingCartItem> findByShoppingCartIdAndProductId(Long cartId, Long productId);
}
