package edu.member.student.repository;

import edu.member.student.entity.Profit;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfitRepossitory extends Neo4jRepository<Profit,String> {
}
