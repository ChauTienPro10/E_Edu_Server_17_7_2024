package com.edu.app.repository;

import com.edu.app.entity.InvalidatedToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvalidtedTokenRepository extends JpaRepository<InvalidatedToken,String> {
}
