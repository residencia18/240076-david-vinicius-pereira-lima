package com.example.atividades.atividade09;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ItemCollectionTest {
    private ItemCollection itemCollection;
    private Faker faker;
    private Item item;

    @BeforeEach
    void setUp() {
        itemCollection = new ItemCollection();
        faker = new Faker();
        item = new Item(faker.commerce().productName());
    }

    @Test
    void testAddItem() {
        itemCollection.addItem(item);
        List<Item> items = itemCollection.getItems();
        assertTrue(((List<?>) items).contains(item), "The item should be in the collection");
    }

    @Test
    void testAddNullItem() {
        assertThrows(IllegalArgumentException.class, () -> itemCollection.addItem(null),
                "Should throw IllegalArgumentException when adding null item");
    }

    @Test
    void testRemoveItem() {
        itemCollection.addItem(item);
        itemCollection.removeItem(item);
        List<Item> items = itemCollection.getItems();
        assertFalse(items.contains(item), "The item should be removed from the collection");
    }

    @Test
    void testRemoveItemNotFound() {
        Item anotherItem = new Item(faker.commerce().productName());
        assertThrows(IllegalArgumentException.class, () -> itemCollection.removeItem(anotherItem),
                "Should throw IllegalArgumentException when removing an item not in the collection");
    }

    @Test
    void testGetItems() {
        itemCollection.addItem(item);
        List<Item> items = itemCollection.getItems();
        assertNotNull(items, "The items list should not be null");
        assertEquals(1, items.size(), "The items list should contain exactly one item");
        assertEquals(item, items.get(0), "The item in the list should be the added item");
    }
}
