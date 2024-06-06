package com.example.atividades.atividade09;

import com.example.atividades.atividade07.User;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ItemTest {
    private Item item;
    private String name;
    private Faker faker = new Faker(Locale.ENGLISH);

    @BeforeEach
    void setUp(){
        name = faker.commerce().productName();
        item = new Item(name);
    }

    @Test
    void testGetName(){
        assertEquals(name, item.getName(), "The name should be " + name);
    }

    @Test
    void testNameIsNull(){
        Item newItem = new Item(null);
        assertNull(newItem.getName(), "The name should be null");
    }

    @Test
    void testNameIsEmpty(){
        Item newItem = new Item("");
        assertEquals("", newItem.getName(), "The name should be an empty string");
    }
}
