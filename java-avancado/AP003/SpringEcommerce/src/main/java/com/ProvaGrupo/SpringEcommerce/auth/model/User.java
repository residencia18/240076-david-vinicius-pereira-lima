package com.ProvaGrupo.SpringEcommerce.auth.model;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ProvaGrupo.SpringEcommerce.auth.util.validator.ValidPassword;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A user entity that implements the UserDetails interface to be used by Spring Security. 
 * The entity has a OneTimePassword embedded class to store the OTP and its generation time.
 * 
 * Each user has an id, a username, a password, an email, a boolean to check if the user is enabled, a role and an OTP.
 * 
 * @see OneTimePassword 
 * @see UserRole
 * @see UserDetails
 * 
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Builder
public class User implements UserDetails {

    /**
     * The id of the user. It is generated automatically by the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The login of the user. It must be unique and cannot be blank.
     * It must have between 5 and 15 characters.
     */
    @NotBlank(message = "Username cannot be blank")
    @Size(min = 5, max = 16, message = "Username must be between 5 and 16 characters long")
    @Column(unique = true, nullable = false)
    private String username;

    /**
     * The password of the user. It must be valid according to the ValidPassword annotation.
     * @see ValidPassword
     */
    @ValidPassword
    private String password;

    /**
     * The email of the user. It must be unique and a valid email and cannot be blank.
     */
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    @Column(unique = true, nullable = false)
    private String email;

    /**
     * The wishlists of the user. It is a set of wishlists.
     */
    // @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // private Set<Wishlist> wishlists;

    /**
     * A boolean to check if the user is enabled.
     * It is false by default.
     */
    @Column(name = "is_enabled", columnDefinition = "boolean default false")
    private boolean enabled;
    
    /**
     * The role of the user. It is a UserRole enum that can be either USER or ADMIN.
     */
    private UserRole role;

    /**
     * Embedded class to store the OTP and its generation time.
     */
    @Embedded
    private OneTimePassword otp;
    

    /**
     * Constructor for the User entity.
     *
     * @param username username data of the user
     * @param password password data of the user
     * @param email email data of the user
     * @param role role data of the user
     */
    public User(String username, String password, String email, UserRole role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    /**
     * Returns the authorities of the user. 
     * If the user is an admin, it returns both ROLE_ADMIN and ROLE_USER.
     * If the user is not an admin, it returns only ROLE_USER.
     * 
     * @return a collection of authorities of the user
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.ADMIN) {
            return Set.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        } else {
            return Set.of(() -> "ROLE_USER");
        }
    }

    /**
     * Returns the password of the user.
     */
    @Override
    public String getUsername() {
        return this.username;
    }
    
    /**
     * Returns a boolean to check if the user is not expired.
     * 
     * @return default true
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Returns a boolean to check if the user is not locked.
     * 
     * @return default true
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Returns a boolean to check if the user's credentials are not expired.
     * 
     * @return default true
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Returns a boolean to check if the user is enabled.
     * 
     * @return the status of the user (enabled or not)
     */
    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

}
