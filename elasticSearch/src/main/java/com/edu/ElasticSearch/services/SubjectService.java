package com.edu.ElasticSearch.services;

import com.edu.ElasticSearch.dto.response.ApiResponse;
import com.edu.ElasticSearch.entity.Subject;
import com.edu.ElasticSearch.exception.ErrorCode;
import com.edu.ElasticSearch.repository.SubjectRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class SubjectService {
    SubjectRepository subjectRepository;

    public ApiResponse<Subject> newSubject(Subject subject){
        if(subjectRepository.findByCode(subject.getCode()).isPresent()){
            return ApiResponse.<Subject>builder()
                    .code(ErrorCode.ERROR_SUBJECT_CODE_EXIST.getCode())
                    .message(ErrorCode.ERROR_SUBJECT_CODE_EXIST.getMessage())
                    .result(null)
                    .build();
        }
        if(subjectRepository.findByName(subject.getName()).isPresent()){
            return ApiResponse.<Subject>builder()
                    .code(ErrorCode.ERROR_SUBJECT_NAME_EXIST.getCode())
                    .message(ErrorCode.ERROR_SUBJECT_NAME_EXIST.getMessage())
                    .result(null)
                    .build();
        }
        return ApiResponse.<Subject>builder()
                .code(1000)
                .message("OK")
                .result(subjectRepository.save(subject))
                .build();
    }
}
