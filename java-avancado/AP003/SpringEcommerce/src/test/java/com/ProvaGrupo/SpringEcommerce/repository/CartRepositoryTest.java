package com.ProvaGrupo.SpringEcommerce.repository;

import com.ProvaGrupo.SpringEcommerce.model.*;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@AutoConfigureTestDatabase
@DataJpaTest
public class CartRepositoryTest {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private Faker faker = new Faker(new Locale("en-US"));
    private static final Logger log = LoggerFactory.getLogger(ShoppingCartItemTest.class);

    private ShoppingCart cart;
    private Product product;

    @BeforeEach
    public void setup(){

        Category category = Category.builder()
                .name("Electronics")
                .possibleFacets(new ArrayList<>())
                .build();

        product = Product.builder()
                .name(faker.commerce().productName())
                .description("Example product description")
                .price(new BigDecimal("99.99"))
                .sku(faker.regexify("[a-zA-Z0-9]{2,50}"))
                .imageUrl("https://example.com/image.jpg")
                .category(category)
                .quantity(10)
                .manufacturer("Example Manufacturer")
                .featured(true)
                .build();

        BigDecimal price = BigDecimal.valueOf(faker.random().nextDouble()).setScale(2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(faker.random().nextInt(9999)));
        cart = ShoppingCart.builder()
                .id(null)
                .cartTotalPrice(price)
                .numberOfItems(faker.random().nextInt(999))
                .username(faker.name().username())
                .build();

        log.info("Setup complete. Generated shopping cart: {}", cart);
    }

    @Test
    public void testSaveAndFindById(){
        log.info("--- Running SaveAndFindById ---\n");
        ShoppingCart savedCart = cartRepository.save(cart);
        log.info("Saved cart: {}", savedCart);
        Optional<ShoppingCart> foundCart = cartRepository.findById(savedCart.getId());
        assertTrue(foundCart.isPresent());
        log.info("Found cart by ID: {}", foundCart.get());
        assertEquals(savedCart.getId(), foundCart.get().getId());

    }

    @Test
    public void testFindByUsername(){
        log.info("--- Running FindByUsername ---\n");
        ShoppingCart savedCart = cartRepository.save(cart);
        log.info("Saved cart: {}", savedCart);
        Optional<ShoppingCart> foundCart = cartRepository.findByUsername(savedCart.getUsername());
        assertTrue(foundCart.isPresent());
        log.info("Found cart by Username: {}", foundCart.get());
        assertEquals(savedCart.getUsername(), foundCart.get().getUsername());
    }

    @Test
    public void testFindByIdNotFound(){
        log.info("--- Running FindByIDNotFound ---\n");
        Optional<ShoppingCart> foundCart = cartRepository.findById(9999L);
        log.info("Result of Find By ID Not Found: {}", foundCart);
        assertTrue(foundCart.isEmpty());
    }

    @Test
    public void testFindByUsernameNotFound(){
        log.info("--- Running FindByUsernameNotFound ---\n");
        Optional<ShoppingCart> foundCart = cartRepository.findByUsername(faker.name().username());
        log.info("Result of Find By Username Not Found: {}", foundCart);
        assertTrue(foundCart.isEmpty());
    }

    @Test
    public void testSaveAndDelete(){
        log.info("--- Running SaveAndDelete ---\n");
        ShoppingCart savedCart = cartRepository.save(cart);
        log.info("Saved cart: {}", savedCart);
        cartRepository.deleteById(savedCart.getId());
        log.info("Deleted cart with ID: {}", savedCart.getId());
        Optional<ShoppingCart> foundCart = cartRepository.findById(savedCart.getId());
        log.info("Result of Save and Delete after delete: {}", foundCart);
        assertTrue(foundCart.isEmpty());
    }

    @Test
    public void testSaveWithItems(){
        log.info("--- Running SaveWithItems ---\n");
        ShoppingCartItem item = ShoppingCartItem.builder()
                .name(faker.commerce().productName())
                .price(BigDecimal.valueOf(faker.random().nextDouble()).setScale(2, RoundingMode.HALF_UP))
                .product(product)
                .shoppingCart(cart)
                .build();
        List<ShoppingCartItem> items = new ArrayList<>();
        items.add(item);

        cart.setShoppingCartItems(items);
        log.info("Cart with items: {}", cart);

        ShoppingCart savedCart = cartRepository.save(cart);
        log.info("Saved cart: {}", savedCart);
        Optional<ShoppingCart> foundCart = cartRepository.findById(savedCart.getId());
        log.info("Found cart by ID: {}", foundCart.orElse(null));
        assertTrue(foundCart.isPresent());
        assertEquals(1 , foundCart.get().getShoppingCartItems().size());
        assertEquals(savedCart.getShoppingCartItems().get(0).getName(), foundCart.get().getShoppingCartItems().get(0).getName());
    }
}
