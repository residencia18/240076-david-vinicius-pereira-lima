package com.ProvaGrupo.SpringEcommerce.auth.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ProvaGrupo.SpringEcommerce.auth.model.User;
import com.ProvaGrupo.SpringEcommerce.auth.repository.UserRepository;

/**
 * This service is responsible for handling the authorization operations.
 */
@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * This method will load the user by its username.
     * @param username Username to load the user
     * @throws UsernameNotFoundException if the user is not found
     * @return UserDetails object with the user information
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    /**
     * This method will get the current user.
     * @return UserDetails object with the current user information
     */
    public Optional<User> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        return Optional.of((User) authentication.getPrincipal());
    }    
}