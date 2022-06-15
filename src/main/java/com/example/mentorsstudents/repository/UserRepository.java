package com.example.mentorsstudents.repository;

import com.example.mentorsstudents.entity.User;
import com.example.mentorsstudents.entity.enumiration.UserRole;
import com.example.mentorsstudents.entity.enumiration.UserStatus;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @EntityGraph(attributePaths = {"subjects"})
    List<User> findAllByUserRole(UserRole userRole);

    @Query("from User user where user.email = :login")
    Optional<User> findByLogin(@Param("login") String login);

    @Modifying
    @Query("update User u set u.password = :password where u.userId = :userId")
    void updatePasswordByUserId(@Param("userId") Long userId, @Param("password") String password);

    @Query("select u.password from User u where u.userId =:userId")
    Optional<String> findPasswordByUserId(@Param("userId") Long userId);

    @Modifying
    @Transactional
    @Query("update User u set u.userStatus = :userStatus where u.userId = :userId")
    void setUserStatus(@Param("userStatus") UserStatus userStatus, @Param("userId") Long userId);
}
