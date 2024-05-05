package org.avaliacao.ap002.auth.entity.user;

public record RegisterDTO(String email, String password, UserRole role) {
}
