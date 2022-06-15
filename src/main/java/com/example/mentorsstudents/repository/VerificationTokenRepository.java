package com.example.mentorsstudents.repository;

import com.example.mentorsstudents.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    @Query("from VerificationToken vereficationToken where vereficationToken.token = :token")
    Optional<VerificationToken> getUserVerificationToken(@Param("token") String token);
}
