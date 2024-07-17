package com.edu.app.repository;

import com.edu.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    Boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);
}
