package com.edu.ElasticSearch.repository;

import com.edu.ElasticSearch.entity.VertiCode;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerCodeRepository extends ElasticsearchRepository<VertiCode,String> {
    VertiCode findByVercode(String code);
}
