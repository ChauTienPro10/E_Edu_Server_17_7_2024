package com.edu.ElasticSearch.repository;

import com.edu.ElasticSearch.entity.Teacher;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface TeacherRepository extends ElasticsearchRepository<Teacher,String> {

    Optional<Teacher> findById(String teacher);

    Optional<Teacher> save(Teacher request);
}
