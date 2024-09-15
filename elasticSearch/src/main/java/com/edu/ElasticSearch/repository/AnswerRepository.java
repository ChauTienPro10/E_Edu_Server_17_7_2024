package com.edu.ElasticSearch.repository;

import com.edu.ElasticSearch.entity.Answer;
import com.edu.ElasticSearch.entity.Course;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends ElasticsearchRepository<Answer,String> {
    Answer save(Answer ans);
}
