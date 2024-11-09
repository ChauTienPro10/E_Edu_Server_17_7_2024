package com.edu.ElasticSearch.repository;

import com.edu.ElasticSearch.entity.Practice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PracticeRepository extends ElasticsearchRepository<Practice, String> {
    Practice save(Practice practice);
    Optional<List<Practice>> findByCourseId(String id);
    Optional<Practice> findById(String id);
}
