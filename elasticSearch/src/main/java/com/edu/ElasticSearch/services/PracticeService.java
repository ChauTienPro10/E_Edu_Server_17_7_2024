package com.edu.ElasticSearch.services;

import com.edu.ElasticSearch.dto.request.CreateNewPracticeRequest;
import com.edu.ElasticSearch.dto.response.ApiResponse;
import com.edu.ElasticSearch.entity.Practice;
import com.edu.ElasticSearch.exception.ErrorCode;
import com.edu.ElasticSearch.repository.CourseRepository;
import com.edu.ElasticSearch.repository.PracticeRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PracticeService {
    @Autowired
    PracticeRepository practiceRepository;
    @Autowired
    CourseRepository courseRepository;

    public ApiResponse<Practice> CreateNewPractice(CreateNewPracticeRequest request){
        if(courseRepository.findById(request.getCourseId()).isEmpty()){
            return ApiResponse.<Practice>builder()
                    .code(ErrorCode.ERR_PRACTICE_COURAE_NOT_FOUND.getCode())
                    .message(ErrorCode.ERR_PRACTICE_COURAE_NOT_FOUND.getMessage())
                    .result(null)
                    .build();
        }

        Practice practice=new Practice();
        practice.setContent(request.getContent());
        practice.setCourseId(request.getCourseId());
        practice.setHardLevel(request.getHardLevel());
        return ApiResponse.<Practice>builder()
                .code(1000)
                .message("OK")
                .result(practiceRepository.save(practice))
                .build();
    }

    public Optional<List<Practice>> findAllByIdOfCourse(String id){
        return practiceRepository.findByCourseId(id);
    }
}
