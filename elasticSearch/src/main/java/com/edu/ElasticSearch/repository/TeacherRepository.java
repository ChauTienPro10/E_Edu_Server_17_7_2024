package com.edu.ElasticSearch.repository;

import com.edu.ElasticSearch.entity.Teacher;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface TeacherRepository extends ElasticsearchRepository<Teacher,String> {

    Optional<Teacher> findById(String teacher);

    Optional<Teacher> save(Teacher request);
    List<Teacher> findAll();
    List<Teacher> findByLevel(int level);
    List<Teacher> findByMajor(String code);
    List<Teacher> findByLevelAndMajor(int level,String major);
    Optional<Teacher> findByEmail(String email);

}
