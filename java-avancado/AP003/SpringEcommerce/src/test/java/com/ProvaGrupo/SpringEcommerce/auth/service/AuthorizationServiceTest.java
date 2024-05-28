package com.ProvaGrupo.SpringEcommerce.auth.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ProvaGrupo.SpringEcommerce.auth.repository.UserRepository;

public class AuthorizationServiceTest {

    private AuthorizationService authorizationService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserDetails userDetails;

    @BeforeEach
    public void init() throws Exception {
        MockitoAnnotations.openMocks(this);
        authorizationService = new AuthorizationService();

        // Injecting the mocked UserRepository into the AuthorizationService
        Field field = AuthorizationService.class.getDeclaredField("userRepository");
        field.setAccessible(true);
        field.set(authorizationService, userRepository); 
    }

    @Test
    public void testLoadUserByUsername_UserFound() {
        String username = "testUser";
        
        // Mocking the behavior of the UserRepository to return a user
        when(userRepository.findByUsername(username)).thenReturn(userDetails);

        // Loading the user by username
        UserDetails result = authorizationService.loadUserByUsername(username);

        // Verifying if the user was found
        assertEquals(userDetails, result);
    }

    @Test
    public void testLoadUserByUsername_UserNotFound() {
        String username = "testUser";

        // Mocking the behavior of the UserRepository to return null
        when(userRepository.findByUsername(username)).thenReturn(null);

        // Loading a user by username that does not exist and expecting an exception
        assertThrows(UsernameNotFoundException.class, () -> {
            authorizationService.loadUserByUsername(username);
        });
    }
}