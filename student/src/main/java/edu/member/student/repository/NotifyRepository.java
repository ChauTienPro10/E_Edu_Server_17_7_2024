package edu.member.student.repository;

import edu.member.student.entity.Notify;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotifyRepository extends Neo4jRepository<Notify,String> {

}
