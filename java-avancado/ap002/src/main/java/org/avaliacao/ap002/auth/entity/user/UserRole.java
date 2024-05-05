package org.avaliacao.ap002.auth.entity.user;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("admin"),
    USER("user");

    private String role;

    UserRole(String role){
        this.role = role;
    }

}
