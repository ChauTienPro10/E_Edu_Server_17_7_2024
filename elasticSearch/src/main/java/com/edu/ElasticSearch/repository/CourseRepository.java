package com.edu.ElasticSearch.repository;


import com.edu.ElasticSearch.entity.Course;
import org.elasticsearch.common.recycler.Recycler;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends ElasticsearchRepository<Course,String> {
    List<Course> findByLevel(int level);
    @Query("{\"match\": {\"id\": \"?0\"}}")
    List<Course> findAllById(String id);
    @Query("{\"query_string\": {\"query\": \"?0\", \"fields\": [\"name\", \"description\"]}}")
    List<Course> searchByQueryString(String text);
    List<Course> findByTeacher(String teacher_id);
    Course save(Course course);
    List<Course> findAll();
    Optional<Course> findByTitle(String title);
    Optional<Course> findById(String id);
}
