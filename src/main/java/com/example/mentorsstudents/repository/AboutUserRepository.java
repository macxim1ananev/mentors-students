package com.example.mentorsstudents.repository;

import com.example.mentorsstudents.entity.AboutUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AboutUserRepository extends JpaRepository<AboutUser, Long> {
}
