package edu.member.student.repository;

import edu.member.student.entity.AccountPay;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface PayRepository extends Neo4jRepository<AccountPay,String> {

    Optional<AccountPay> findByEmail(String email);
}
