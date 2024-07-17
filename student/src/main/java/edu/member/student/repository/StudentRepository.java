package edu.member.student.repository;

import edu.member.student.entity.Student;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends Neo4jRepository<Student,String> {
}
