package com.edu.ElasticSearch.services;

import com.edu.ElasticSearch.entity.Teacher;
import com.edu.ElasticSearch.repository.TeacherRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class TeacherService {
    TeacherRepository teacherRepository;
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    public Teacher createNewTeacher(Teacher teacher) {
        try {
            // Save the Teacher document to Elasticsearch
            return elasticsearchRestTemplate.save(teacher);
        } catch (Exception e) {
            // Log detailed error
            System.err.println("Error creating new teacher: " + e.getMessage());
            return null;
        }
    }


}
