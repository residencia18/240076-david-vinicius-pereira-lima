package org.avaliacao.ap002.auth.repository;

import org.avaliacao.ap002.auth.entity.ForgotPassword;
import org.avaliacao.ap002.auth.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ForgotPasswordRepository extends JpaRepository<ForgotPassword, Long> {
    @Query("select fp from ForgotPassword fp where fp.oneTimePass = ?1 and fp.user = ?2")
    Optional<ForgotPassword>findByOneTimePassAndUser(Integer otp, User user);
}
