package com.edu.ElasticSearch.services;

import com.edu.ElasticSearch.dto.response.ApiResponse;
import com.edu.ElasticSearch.entity.Teacher;
import com.edu.ElasticSearch.exception.TeacherException;
import com.edu.ElasticSearch.repository.SubjectRepository;
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
    SubjectRepository subjectRepository;
    public ApiResponse<Teacher> createNewTeacher(Teacher teacher) {
        try {
            if(subjectRepository.findByCode(teacher.getMajor()).isEmpty()){
                return ApiResponse.<Teacher>builder()
                        .code(TeacherException.TEACHER_CREATE_SUBJECT_CODE_INVALID.getCode())
                        .message(TeacherException.TEACHER_CREATE_SUBJECT_CODE_INVALID.getMessage())
                        .result(null)
                        .build();
            }
            if(teacher.getLevel()<1 || teacher.getLevel()>4){
                return ApiResponse.<Teacher>builder()
                        .code(TeacherException.TEACHER_CREATE_LEVEL_INVALID.getCode())
                        .message(TeacherException.TEACHER_CREATE_LEVEL_INVALID.getMessage())
                        .result(null)
                        .build();
            }
            // Save the Teacher document to Elasticsearch
            return ApiResponse.<Teacher>builder()
                    .code(1000)
                    .message("OK")
                    .result(elasticsearchRestTemplate.save(teacher))
                    .build();

        } catch (Exception e) {
            // Log detailed error
            System.err.println("Error creating new teacher: " + e.getMessage());
            return null;
        }
    }


}
