package com.edu.ElasticSearch.repository;


import com.edu.ElasticSearch.entity.Course;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends ElasticsearchRepository<Course,String> {
    List<Course> findByLevel(int level);

    @Query("{\"query_string\": {\"query\": \"?0\", \"fields\": [\"name\", \"description\"]}}")
    List<Course> searchByQueryString(String text);

    void save(Course course);
    List<Course> findAll();
}
