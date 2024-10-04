package edu.member.student.repository;


import edu.member.student.entity.VerifyCode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VerifyCodeRepository extends Neo4jRepository<VerifyCode,String> {
    Optional<VerifyCode> findByEmailAndCode(String email, String code);
    List<Optional<VerifyCode>> findByEmail(String email);
}
