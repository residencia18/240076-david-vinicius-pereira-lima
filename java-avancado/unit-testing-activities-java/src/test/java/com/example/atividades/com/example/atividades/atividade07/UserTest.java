package com.example.atividades.atividade07;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UserTest {
    private User user;
    private String name;
    private String email;
    private Faker faker = new Faker(Locale.ENGLISH);

    @BeforeEach
    void setUp() {
        name = faker.name().fullName();
        email = faker.internet().emailAddress();
        user = new User(name, email);
    }

    @Test
    void testGetName() {
        assertEquals(name, user.getName(), "The name should be " + name);
    }

    @Test
    void testGetEmail() {
        assertEquals(email, user.getEmail(), "The email should be " + email);
    }

    @Test
    void testNameIsNotNull() {
        User user = new User(null, email);
        assertNull(user.getName(), "The name should be null");
    }

    @Test
    void testNameIsEmpty() {
        User user = new User("", email);
        assertEquals("", user.getName(), "The name should be an empty string");
    }

    @Test
    void testEmailIsNotNull() {
        User user = new User(name, null);
        assertNull(user.getEmail(), "The email should be null");
    }

    @Test
    void testEmailIsEmpty() {
        User user = new User(name, "");
        assertEquals("", user.getEmail(), "The email should be an empty string");
    }
}
