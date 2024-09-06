package com.edu.ElasticSearch.repository;

import com.edu.ElasticSearch.entity.Teacher;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface TeacherRepository extends ElasticsearchRepository<Teacher,String> {

}
