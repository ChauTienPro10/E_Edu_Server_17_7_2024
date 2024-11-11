package com.edu.ElasticSearch.repository;

import com.edu.ElasticSearch.entity.PracticeResolve;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PracticeResolveRepository extends ElasticsearchRepository<PracticeResolve,String> {
    PracticeResolve save(PracticeResolve practiceResolve);
    List<PracticeResolve> findAllByPracticeId(String practiceId);
    Optional<PracticeResolve> findById(String id);

}
