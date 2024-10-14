package edu.member.student.repository;

import edu.member.student.entity.RegisterCourse;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegisterRepository extends Neo4jRepository<RegisterCourse,String> {
    List<Optional<RegisterCourse>> findByEmail(String email);
    Optional<RegisterCourse> findByEmailAndCourse(String email, String courseId);
    @Query("MATCH (r:register) RETURN COUNT(DISTINCT r.email)")
    long countUniqueStudents();
}
