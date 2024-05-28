package com.ProvaGrupo.SpringEcommerce.repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ProvaGrupo.SpringEcommerce.model.VerificationToken;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    Optional<VerificationToken> findByToken(String token); 
    List<VerificationToken> findByUserId(Long userId);
    Optional<VerificationToken> findByExpiryDate(Instant expiryDate);
    Optional<VerificationToken> findByTokenAndUserId(String token, Long userId);

	void deleteByExpiryDateBefore(Instant before);
	List<VerificationToken> findByExpiryDateBetween(Instant startRange, Instant endRange);
	boolean existsByExpiryDate(Instant expectedExpiryDate);
}