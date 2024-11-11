package com.edu.ElasticSearch.repository;

import com.edu.ElasticSearch.entity.Like;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;

public interface LikeRepository extends ElasticsearchRepository <Like,String> {
    Like save(Like like);
    Optional<Like> findByEmailAndPracticeResolveId(String email,String idResolve);
}
