package org.avaliacao.ap002.test_entity;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.avaliacao.ap002.auth.entity.user.User;
import org.avaliacao.ap002.auth.entity.user.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    private User user;
    private Validator validator;

    @BeforeEach
    public void setUp() {
        user = User.builder()
                .id(1L)
                .email("test@example.com")
                .password("password")
                .role(UserRole.USER)
                .build();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testGetAuthorities_UserRoleUser() {
        Collection<? extends SimpleGrantedAuthority> authorities = (Collection<? extends SimpleGrantedAuthority>) user.getAuthorities();
        assertEquals(1, authorities.size());
        assertTrue(authorities.contains(new SimpleGrantedAuthority("ROLE_USER")));
        assertFalse(authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN")));
    }

    @Test
    public void testGetAuthorities_UserRoleAdmin() {
        user.setRole(UserRole.ADMIN);
        Collection<? extends SimpleGrantedAuthority> authorities = (Collection<? extends SimpleGrantedAuthority>) user.getAuthorities();
        assertEquals(2, authorities.size());
        assertTrue(authorities.contains(new SimpleGrantedAuthority("ROLE_USER")));
        assertTrue(authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN")));
    }

    @Test
    public void testGetUsername() {
        assertEquals("test@example.com", user.getUsername());
    }

    @Test
    public void testIsAccountNonExpired() {
        assertTrue(user.isAccountNonExpired());
    }

    @Test
    public void testIsAccountNonLocked() {
        assertTrue(user.isAccountNonLocked());
    }

    @Test
    public void testIsCredentialsNonExpired() {
        assertTrue(user.isCredentialsNonExpired());
    }

    @Test
    public void testIsEnabled() {
        assertTrue(user.isEnabled());
    }

    @Test
    public void testValidPassword_Valid() {
        user.setPassword("StrongPassword123!");
        assertTrue(validator.validate(user).isEmpty());
    }

    @Test
    public void testValidPassword_TooShort() {
        user.setPassword("Short1!");
        assertFalse(validator.validate(user).isEmpty());
    }

    @Test
    public void testValidPassword_NoUppercaseLetter() {
        user.setPassword("weakpassword123!");
        assertFalse(validator.validate(user).isEmpty());
    }

    @Test
    public void testValidPassword_NoLowercaseLetter() {
        user.setPassword("WEAKPASSWORD123!");
        assertFalse(validator.validate(user).isEmpty());
    }

    @Test
    public void testValidPassword_NoDigit() {
        user.setPassword("WeakPassword!");
        assertFalse(validator.validate(user).isEmpty());
    }

    @Test
    public void testValidPassword_NoSpecialCharacter() {
        user.setPassword("WeakPassword123");
        assertFalse(validator.validate(user).isEmpty());
    }

    @Test
    public void testValidPassword_CommonPassword() {
        user.setPassword("password");
        assertFalse(validator.validate(user).isEmpty());
    }
}
