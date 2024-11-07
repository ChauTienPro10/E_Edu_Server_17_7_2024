package com.edu.ElasticSearch.repository;

import com.edu.ElasticSearch.entity.PracticeResolve;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PracticeResolveRepository extends ElasticsearchRepository<PracticeResolve,String> {

}
