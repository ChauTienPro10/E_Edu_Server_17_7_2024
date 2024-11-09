package com.edu.ElasticSearch.repository;

import com.edu.ElasticSearch.entity.ObserveOfResolve;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObserveOfResolveRepository extends ElasticsearchRepository<ObserveOfResolve,String> {
    ObserveOfResolve save(ObserveOfResolve observe);
}
