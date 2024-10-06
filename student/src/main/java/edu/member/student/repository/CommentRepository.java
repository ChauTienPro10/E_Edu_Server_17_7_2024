package edu.member.student.repository;

import edu.member.student.entity.Comment;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends Neo4jRepository<Comment,String> {
    List<Comment> findByCourse(String Course); // lay tat ca comment cua khoa hoc
}
