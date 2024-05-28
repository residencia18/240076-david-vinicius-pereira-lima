package com.ProvaGrupo.SpringEcommerce.controller;

import static org.springframework.http.ResponseEntity.status;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProvaGrupo.SpringEcommerce.exception.SpringStoreException;
import com.ProvaGrupo.SpringEcommerce.service.CartSevice;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/cart/")
@AllArgsConstructor
@Tag(name = "Cart", description = "Operations related to the shopping cart")
public class CartController {

	private final CartSevice cartSevice;

	@PostMapping("/add/{sku}")
	@Operation(summary = "Add an item to the cart", description = "Add an item to the shopping cart using its SKU")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "Item successfully added to the cart"),
		@ApiResponse(responseCode = "400", description = "Error adding item to the cart")
	})
	public ResponseEntity<Void> addToCart(
			@PathVariable @Parameter(description = "SKU of the item to add", required = true) String sku) {
		try {
			log.info("Attempting to add item with SKU: {} to cart", sku);
			cartSevice.addToCart(sku);
			log.info("Successfully added item with SKU: {} to cart", sku);
			return status(HttpStatus.CREATED).build();
		} catch (SpringStoreException e) {
			log.error("Error adding item with SKU: {} to cart", sku, e);
			return status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@DeleteMapping("/remove/{sku}")
	@Operation(summary = "Remove an item from the cart", description = "Remove an item from the shopping cart using its SKU")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "204", description = "Item successfully removed from the cart"),
		@ApiResponse(responseCode = "400", description = "Error removing item from the cart")
	})
	public ResponseEntity<Void> removeFromCart(
			@PathVariable @Parameter(description = "SKU of the item to remove", required = true) String sku) {
		try {
			log.info("Attempting to remove item with SKU: {} from cart", sku);
			cartSevice.removeFromCart(sku);
			log.info("Successfully removed item with SKU: {} from cart", sku);
			return ResponseEntity.noContent().build();
		} catch (SpringStoreException e) {
			log.error("Error removing item with SKU: {} from cart", sku, e);
			return status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@PostMapping("/update/{sku}/{quantity}")
	@Operation(summary = "Update item quantity in the cart", description = "Update the quantity of an item in the cart using its SKU")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Item quantity successfully updated"),
		@ApiResponse(responseCode = "400", description = "Error updating item quantity in the cart")
	})
	public ResponseEntity<Void> updateItemQuantityInCart(
			@PathVariable @Parameter(description = "SKU of the item to update", required = true) String sku,
			@PathVariable @Parameter(description = "New quantity of the item", required = true) int quantity) {
		try {
			log.info("Attempting to update quantity of item with SKU: {} to {} in cart", sku, quantity);
			cartSevice.updateItemQuantityInCart(sku, quantity);
			log.info("Successfully updated quantity of item with SKU: {} to {} in cart", sku, quantity);
			return ResponseEntity.ok().build();
		} catch (SpringStoreException e) {
			log.error("Error updating quantity of item with SKU: {} in cart", sku, e);
			return status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@DeleteMapping("/clear")
	@Operation(summary = "Clear the cart", description = "Remove all items from the shopping cart")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "204", description = "Cart successfully cleared"),
		@ApiResponse(responseCode = "400", description = "Error clearing the cart")
	})
	public ResponseEntity<Void> clearCart() {
		try {
			log.info("Attempting to clear the cart");
			cartSevice.clearCart();
			log.info("Successfully cleared the cart");
			return ResponseEntity.noContent().build();
		} catch (SpringStoreException e) {
			log.error("Error clearing the cart", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
}
