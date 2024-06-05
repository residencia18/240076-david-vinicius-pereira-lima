package com.example.atividades.atividade07;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class DatabaseTest {
    private Database database;
    private User user;
    private Faker faker = new Faker(Locale.ENGLISH);

    @BeforeEach
    void setUp() {
        database = new Database();
        user = new User(faker.name().fullName(), faker.internet().emailAddress());
    }

    @Test
    void testSaveUser() {

        assertDoesNotThrow(() -> {
            database.saveUser(user);
        });
    }
}
