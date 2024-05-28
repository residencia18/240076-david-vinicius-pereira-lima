package com.ProvaGrupo.SpringEcommerce.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ProvaGrupo.SpringEcommerce.service.CartSevice;

@ExtendWith(MockitoExtension.class)
public class CartControllerTest {

    private static final Logger log = LoggerFactory.getLogger(CartControllerTest.class);

    @Mock
    private CartSevice cartSevice;

    @InjectMocks
    private CartController cartController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(cartController).build();
    }

    @Test
    public void testAddToCart() throws Exception{
        log.info("--- Running AddToCart ---\n");
        String sku = "test-sku";
        log.info("Testing add to cart with SKU: {}", sku);
        doNothing().when(cartSevice).addToCart(anyString());

        mockMvc.perform(post("/api/cart/add/{sku}", sku))
                .andExpect(status().isCreated());

        verify(cartSevice, times(1)).addToCart(sku);
        log.info("Completed test: AddToCart");
    }

    @Test
    public void testRemoveFromCart() throws Exception {
        log.info("--- Running RemoveFromCart ---\n");
        String sku = "test-sku";
        log.info("Testing remove from cart with SKU: {}", sku);

        doNothing().when(cartSevice).removeFromCart(anyString());

        mockMvc.perform(delete("/api/cart/remove/{sku}", sku))
                .andExpect(status().isNoContent());

        verify(cartSevice, times(1)).removeFromCart(sku);
        log.info("Completed test: RemoveFromCart");
    }

    @Test
    public void testUpdateItemQuantityInCart() throws Exception {
        log.info("--- Running UpdateItemQuantityInCart ---\n");
        String sku = "test-sku";
        int quantity = 5;
        log.info("Testing update item quantity in cart with SKU: {} and quantity: {}", sku, quantity);

        doNothing().when(cartSevice).updateItemQuantityInCart(anyString(), anyInt());

        mockMvc.perform(post("/api/cart/update/{sku}/{quantity}", sku, quantity))
                .andExpect(status().isOk());

        verify(cartSevice, times(1)).updateItemQuantityInCart(sku, quantity);
        log.info("Completed test: UpdateItemQuantityInCart");
    }

    @Test
    public void testClearCartSuccessfully(){
        log.info("--- Running ClearCartSuccessfully ---\n");
        doNothing().when(cartSevice).clearCart();

        log.debug("Calling cartController.clearCart()");
        ResponseEntity<Void> response = cartController.clearCart();

        log.debug("Verifying the response status");
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        log.debug("Verifying that cartSevice.clearCart() was called once");
        verify(cartSevice, times(1)).clearCart();

        log.info("Completed test: ClearCartSuccessfully");
    }
}
