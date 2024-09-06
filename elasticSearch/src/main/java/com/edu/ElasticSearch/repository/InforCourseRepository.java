package com.edu.ElasticSearch.repository;

import com.edu.ElasticSearch.entity.InforCourse;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InforCourseRepository extends ElasticsearchRepository<InforCourse,String> {
}
