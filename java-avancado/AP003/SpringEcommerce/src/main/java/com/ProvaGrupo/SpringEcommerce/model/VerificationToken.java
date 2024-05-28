package com.ProvaGrupo.SpringEcommerce.model;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "token")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerificationToken {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Token obrigatório")
	@Column(unique = true, nullable = false)
	private String token;
	
	@NotNull(message = "User obrigatório")
	private Long userId;
	
	@NotNull(message = "ExpiryDate obrigatório")
	@Column(nullable = false)
	private Instant expiryDate;
	
}
