package org.avaliacao.ap002.test_service;

import org.avaliacao.ap002.auth.entity.user.User;
import org.avaliacao.ap002.auth.entity.user.UserRole;
import org.avaliacao.ap002.auth.repository.UserRepository;
import org.avaliacao.ap002.auth.service.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;

@WebMvcTest(AuthService.class)
public class AuthServiceTest {
    @Autowired
    private AuthService authService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void testLoadUserByUsername_Success() {
        User user = new User("test@example.com", "password", UserRole.USER);
        when(userRepository.findByEmail("test@example.com")).thenReturn(user);

        UserDetails userDetails = authService.loadUserByUsername("test@example.com");

        assertNotNull(userDetails);
        assertEquals(user.getEmail(), userDetails.getUsername());
        assertEquals(user.getPassword(), userDetails.getPassword());
    }

    @Test
    public void testLoadUserByUsername_UserNotFound() {
        when(userRepository.findByEmail("nonexistent@example.com")).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> authService.loadUserByUsername("nonexistent@example.com"));
    }
}
