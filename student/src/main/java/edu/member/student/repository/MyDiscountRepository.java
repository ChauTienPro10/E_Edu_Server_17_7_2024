package edu.member.student.repository;

import edu.member.student.entity.MyDiscount;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MyDiscountRepository extends Neo4jRepository<MyDiscount,String> {

    Optional<MyDiscount> findByStudent(String student);
    Optional<MyDiscount> findByDiscountcode(String code);
}
