package com.example.mentorsstudents.repository;

import com.example.mentorsstudents.entity.Subject;
import com.example.mentorsstudents.entity.User;
import com.example.mentorsstudents.entity.enumiration.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByUserRole(UserRole userRole );

}
