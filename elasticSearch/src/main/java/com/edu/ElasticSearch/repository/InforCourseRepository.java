package com.edu.ElasticSearch.repository;

import com.edu.ElasticSearch.entity.InforCourse;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InforCourseRepository extends ElasticsearchRepository<InforCourse,String> {
    Optional<InforCourse> findByCourse(String course);
}
