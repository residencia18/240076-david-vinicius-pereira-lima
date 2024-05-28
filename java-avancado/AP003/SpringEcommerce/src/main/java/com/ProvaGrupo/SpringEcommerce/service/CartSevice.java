package com.ProvaGrupo.SpringEcommerce.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ProvaGrupo.SpringEcommerce.auth.model.User;
import com.ProvaGrupo.SpringEcommerce.auth.service.AuthorizationService;
import com.ProvaGrupo.SpringEcommerce.exception.SpringStoreException;
import com.ProvaGrupo.SpringEcommerce.model.Product;
import com.ProvaGrupo.SpringEcommerce.model.ShoppingCart;
import com.ProvaGrupo.SpringEcommerce.model.ShoppingCartItem;
import com.ProvaGrupo.SpringEcommerce.repository.CartRepository;
import com.ProvaGrupo.SpringEcommerce.repository.ProductRepository;
import com.ProvaGrupo.SpringEcommerce.repository.ShoppingCartItemRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CartSevice {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AuthorizationService authService;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ShoppingCartItemRepository shoppingCartItemRepository;

    @Transactional
    public void addToCart(String sku){
        Product product = productRepository.findBySku(sku)
                .orElseThrow(() -> new SpringStoreException("Product with SKU : " + sku + " not found"));

        Optional<User> currentUserOptional = authService.getCurrentUser();

        if (currentUserOptional.isPresent()){
            User currentUser = currentUserOptional.get();
            ShoppingCart cart = cartRepository.findByUsername(currentUser.getUsername())
                    .orElseGet(() -> {
                        ShoppingCart newCart = new ShoppingCart();
                        newCart.setUsername(currentUser.getUsername());
                        newCart.setCartTotalPrice(BigDecimal.ZERO);
                        newCart.setNumberOfItems(0);
                        return newCart;
                    });

            Optional<ShoppingCartItem> existingItem = shoppingCartItemRepository
                    .findByShoppingCartIdAndProductId(cart.getId(), product.getId());

            if (existingItem.isPresent()) {
                ShoppingCartItem item = existingItem.get();
                item.setPrice(product.getPrice());
            }
            else {
                ShoppingCartItem newItem = ShoppingCartItem.builder()
                        .name(product.getName())
                        .price(product.getPrice())
                        .shoppingCart(cart)
                        .product(product)
                        .build();

                shoppingCartItemRepository.save(newItem);
                cart.getShoppingCartItems().add(newItem);
            }
            cart.setCartTotalPrice(cart.getShoppingCartItems().stream()
                    .map(ShoppingCartItem :: getPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal :: add));
            cart.setNumberOfItems(cart.getShoppingCartItems().size());

            cartRepository.save(cart);
        }
        else {
            throw new SpringStoreException("User is not authenticated");
        }
    }

    @Transactional
    public void removeFromCart(String sku){
        Product product = productRepository.findBySku(sku)
                .orElseThrow(() -> new SpringStoreException("Product with SKU : " + sku + " not found"));

        Optional<User> currentUserOptional = authService.getCurrentUser();

        if (currentUserOptional.isPresent()){
            User currentUser = currentUserOptional.get();
            ShoppingCart cart = cartRepository.findByUsername(currentUser.getUsername())
                    .orElseGet(() -> {
                        ShoppingCart newCart = new ShoppingCart();
                        newCart.setUsername(currentUser.getUsername());
                        newCart.setCartTotalPrice(BigDecimal.ZERO);
                        newCart.setNumberOfItems(0);
                        return newCart;
                    });

            Optional<ShoppingCartItem> existingItem = shoppingCartItemRepository
                    .findByShoppingCartIdAndProductId(cart.getId(), product.getId());

            existingItem.ifPresent(item ->{
                cart.getShoppingCartItems().remove(item);
                shoppingCartItemRepository.delete(item);
            });

            cart.setCartTotalPrice(cart.getShoppingCartItems().stream()
                    .map(ShoppingCartItem :: getPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal :: add));
            cart.setNumberOfItems(cart.getShoppingCartItems().size());

            cartRepository.save(cart);
        }else {
            throw new SpringStoreException("User isn't authenticated");
        }
    }

    @Transactional
    public void updateItemQuantityInCart(String sku, int quantity){
        if (quantity <= 0) {
            throw new SpringStoreException("Quantity must be greater than zero");
        }

        Product product = productRepository.findBySku(sku)
                .orElseThrow(() -> new SpringStoreException("Product with SKU : " + sku + " not found"));

        Optional<User> currentUserOptional = authService.getCurrentUser();

        if (currentUserOptional.isPresent()){
            User currentUser = currentUserOptional.get();
            ShoppingCart cart = cartRepository.findByUsername(currentUser.getUsername())
                    .orElseGet(() -> {
                        ShoppingCart newCart = new ShoppingCart();
                        newCart.setUsername(currentUser.getUsername());
                        newCart.setCartTotalPrice(BigDecimal.ZERO);
                        newCart.setNumberOfItems(0);
                        return newCart;
                    });

            Optional<ShoppingCartItem> existingItem = shoppingCartItemRepository
                    .findByShoppingCartIdAndProductId(cart.getId(), product.getId());
            if (existingItem.isPresent()){
                ShoppingCartItem item = existingItem.get();
                cart.getShoppingCartItems().remove(item);
                shoppingCartItemRepository.deleteById(item.getId());

                for(int i = 0; i < quantity; i++){
                    ShoppingCartItem newItem = ShoppingCartItem.builder()
                            .name(product.getName())
                            .price(product.getPrice())
                            .shoppingCart(cart)
                            .product(product)
                            .build();
                    cart.getShoppingCartItems().add(newItem);
                    shoppingCartItemRepository.save(newItem);
                }

                cart.setCartTotalPrice(cart.getShoppingCartItems().stream()
                        .map(ShoppingCartItem :: getPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal :: add));
                cart.setNumberOfItems(cart.getShoppingCartItems().size());

                cartRepository.save(cart);
            }
            else {
                throw new SpringStoreException("Cart not found for user: " + currentUser.getUsername());
            }

        }
        else {
            throw new SpringStoreException("User isn't authenticated");
        }
    }

    @Transactional
    public void clearCart() {
        Optional<User> currentUserOptional = authService.getCurrentUser();

        if (currentUserOptional.isPresent()) {
            User currentUser = currentUserOptional.get();

            ShoppingCart cart = cartRepository.findByUsername(currentUser.getUsername())
                    .orElseThrow(() -> new SpringStoreException("Cart not found for user: " + currentUser.getUsername()));

            cart.getShoppingCartItems().clear();
            cart.setCartTotalPrice(BigDecimal.ZERO);
            cart.setNumberOfItems(0);

            cartRepository.save(cart);
        } else {
            throw new SpringStoreException("User is not authenticated");
        }
    }

}
