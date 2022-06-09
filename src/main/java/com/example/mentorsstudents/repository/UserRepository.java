package com.example.mentorsstudents.repository;

import com.example.mentorsstudents.entity.Subject;
import com.example.mentorsstudents.entity.User;
import com.example.mentorsstudents.entity.enumiration.UserRole;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  
    @EntityGraph(attributePaths = {"subjects"})
    List<User> findAllByUserRole(UserRole userRole );

    @Query("from User user where user.email = :login")
    Optional<User> findByLogin(@Param("login") String login);
}
