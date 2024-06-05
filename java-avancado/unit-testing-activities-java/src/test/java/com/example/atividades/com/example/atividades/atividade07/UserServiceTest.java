package com.example.atividades.atividade07;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private Database database;

    private Faker faker = new Faker(Locale.ENGLISH);

    private User user;

    @BeforeEach
    void setUp(){
        user = new User(faker.name().fullName(), faker.internet().emailAddress());
    }

    @Test
    void testSaveValidUser(){
        User savedUser = user;
        userService.saveUser(savedUser);
        verify(database, times(1)).saveUser(savedUser);
    }

    @Test
    void testSaveNullNameUser(){
        User savedUser = new User(null, user.getEmail());
        assertThrows(IllegalArgumentException.class, ()->{
            userService.saveUser(savedUser);
        });
        verify(database, never()).saveUser(savedUser);
    }

    @Test
    void testSaveEmptyNameUser() {
        User savedUser = new User("", user.getEmail());
        assertThrows(IllegalArgumentException.class, () -> userService.saveUser(savedUser));
        verify(database, never()).saveUser(savedUser);
    }

    @Test
    void testSaveNullEmailUser(){
        User savedUser = new User(user.getName(), null);
        assertThrows(IllegalArgumentException.class, ()->{
            userService.saveUser(savedUser);
        });
        verify(database, never()).saveUser(savedUser);
    }

    @Test
    void testSaveEmptyEmailUser() {
        User savedUser = new User(user.getName(), "");
        assertThrows(IllegalArgumentException.class, () -> userService.saveUser(savedUser));
        verify(database, never()).saveUser(savedUser);
    }

}
