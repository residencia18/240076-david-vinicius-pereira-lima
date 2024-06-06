package com.example.atividades.atividade10;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserManagerTest {
    @InjectMocks
    private UserManager userManager;

    @Mock
    private UserService userService;

    private Faker faker;
    private User user;

    @BeforeEach
    void setUp() {
        faker = new Faker();
        user = new User(faker.name().fullName(), faker.internet().emailAddress());
    }

    @Test
    void testFetchUserInfo() {
        // Simulate the UserService returning a user
        when(userService.getUserInfo(anyInt())).thenReturn(user);

        User result = userManager.fetchUserInfo(1);
        assertEquals(user.getName(), result.getName());
        assertEquals(user.getEmail(), result.getEmail());

        verify(userService, times(1)).getUserInfo(1);
    }

    @Test
    void testFetchUserInfoThrowsException() {
        // Simulate the UserService returning null
        when(userService.getUserInfo(anyInt())).thenReturn(null);

        assertThrows(RuntimeException.class, () -> {
            userManager.fetchUserInfo(1);
        }, "Should throw RuntimeException when user is not found");

        verify(userService, times(1)).getUserInfo(1);
    }
}
