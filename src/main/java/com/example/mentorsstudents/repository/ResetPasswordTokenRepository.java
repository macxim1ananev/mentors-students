package com.example.mentorsstudents.repository;

import com.example.mentorsstudents.entity.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ResetPasswordTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    @Query("from PasswordResetToken passwordResetToken where passwordResetToken.token = :token")
    Optional<PasswordResetToken> getUserPasswordResetToken(@Param("token") String token);
}
