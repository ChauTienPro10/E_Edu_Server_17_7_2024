package edu.member.student.repository;

import edu.member.student.entity.MyVoucher;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MyVoucherRepository extends Neo4jRepository<MyVoucher,String> {

    Optional<MyVoucher> findByStudentEmail(String student);
    Optional<MyVoucher> findByVoucherId(String id);

}
