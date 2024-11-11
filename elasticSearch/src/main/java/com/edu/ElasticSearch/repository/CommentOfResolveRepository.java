package com.edu.ElasticSearch.repository;

import com.edu.ElasticSearch.entity.CommentOfResolve;
import com.edu.ElasticSearch.entity.ObserveOfResolve;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentOfResolveRepository extends ElasticsearchRepository<CommentOfResolve,String> {
    CommentOfResolve save(CommentOfResolve comment);
    CommentOfResolve findByResolveId(String id);
}
