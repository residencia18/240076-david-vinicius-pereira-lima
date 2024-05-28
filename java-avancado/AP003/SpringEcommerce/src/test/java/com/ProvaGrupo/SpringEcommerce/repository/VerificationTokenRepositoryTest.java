package com.ProvaGrupo.SpringEcommerce.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ProvaGrupo.SpringEcommerce.model.VerificationToken;

@DataJpaTest
class VerificationTokenRepositoryTest {

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    private VerificationToken token1;
    private VerificationToken token2;
    private VerificationToken token3; // Token expirado

    @BeforeEach
    void setUp() {
        token1 = new VerificationToken(null, "token1", 1L, Instant.now().plusSeconds(3600));
        token2 = new VerificationToken(null, "token2", 2L, Instant.now().plusSeconds(7200));
        token3 = new VerificationToken(null, "token3", 1L, Instant.now().minusSeconds(3600));
                
        verificationTokenRepository.saveAll(List.of(token1, token2, token3));
    }

    @Test
    void findByTokenShouldReturnToken() {
        Optional<VerificationToken> foundToken = verificationTokenRepository.findByToken("token1");
        assertThat(foundToken).isPresent().contains(token1);
    }

    @Test
    void findByUserIdShouldReturnToken() {
        List<VerificationToken> foundToken = verificationTokenRepository.findByUserId(1L);
        assertThat(foundToken).contains(token1, token3);
    }

    @Test
    void findByExpiryDateShouldReturnToken() {
        Instant expectedExpiryDate = token1.getExpiryDate();
        Instant startRange = expectedExpiryDate.minusSeconds(1); // 1 segundo antes
        Instant endRange = expectedExpiryDate.plusSeconds(1);   // 1 segundo depois

        List<VerificationToken> foundTokens = verificationTokenRepository.findByExpiryDateBetween(startRange, endRange);
        assertThat(foundTokens).contains(token1); 
    }
    
    @Test
    void findByTokenAndUserIdShouldReturnToken() {
        Optional<VerificationToken> foundToken = verificationTokenRepository.findByTokenAndUserId("token1", 1L);
        assertThat(foundToken).isPresent().contains(token1);
    }

    @Test
    void deleteByExpiryDateBeforeShouldDeleteExpiredToken() {
        verificationTokenRepository.deleteByExpiryDateBefore(Instant.now());
        assertThat(verificationTokenRepository.findAll()).containsExactlyInAnyOrder(token1, token2); // token3 deve ter sido deletado
    }
}
