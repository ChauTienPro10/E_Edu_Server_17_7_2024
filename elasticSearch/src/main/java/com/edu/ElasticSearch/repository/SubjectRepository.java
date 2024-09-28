package com.edu.ElasticSearch.repository;

import com.edu.ElasticSearch.entity.Subject;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface SubjectRepository extends ElasticsearchRepository<Subject,String> {
    Subject save(Subject request);
    Optional<Subject> findByCode(String code);
    Optional<Subject> findByName(String name);
}
