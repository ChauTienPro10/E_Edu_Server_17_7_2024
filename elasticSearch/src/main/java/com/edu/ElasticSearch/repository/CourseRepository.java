package com.edu.ElasticSearch.repository;


import com.edu.ElasticSearch.entity.Course;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends ElasticsearchRepository<Course,String> {
}
