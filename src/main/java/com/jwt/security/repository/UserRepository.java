package com.jwt.security.repository;

import com.jwt.security.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query("from User as u where u.email=:username")
    Optional<User> getUserByUserName(@Param("username") String username);
}
