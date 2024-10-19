package edu.member.student.repository;

import edu.member.student.entity.Student;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends Neo4jRepository<Student,String> {
    Student findByEmail(String email);
    @Query("MATCH (s:Student_profile) WHERE s.fullname CONTAINS $name RETURN s")
    List<Student> findByFullnameContains(@Param("name") String name);
    Student findByPhone(String phone);

}
