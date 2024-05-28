package com.ProvaGrupo.SpringEcommerce.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.ProvaGrupo.SpringEcommerce.auth.model.User;

/**
 * This interface is a repository for the User entity.
 */
public interface UserRepository extends JpaRepository<User, Long>{

    /**
     * Find a user by username.
     * 
     * @param username The username of the user.
     * @return The UserDetails object of the user.
     */
    UserDetails findByUsername(String username);

    /**
     * Find a user by email.
     * 
     * @param email The email of the user.
     * @return The user object.
     */
    Optional<User> findByEmail(String email);

    /**
     * Check if a user with the given username exists in the database.
     * 
     * @param username The username of the user.
     */
    boolean existsByUsername(String username);

    /**
     * Check if a user with the given email exists in the database.
     * 
     * @param email The email of the user.
     */
    boolean existsByEmail(String email);
}
