package com.edu.ElasticSearch.controller;

import com.edu.ElasticSearch.dto.response.ApiResponse;
import com.edu.ElasticSearch.entity.Course;
import com.edu.ElasticSearch.entity.Teacher;
import com.edu.ElasticSearch.exception.ErrorCode;
import com.edu.ElasticSearch.services.TeacherService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teacher")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TeacherControker {
    TeacherService teacherService;
    @PostMapping("/new")
    public ApiResponse<Teacher> newTeacher(@RequestBody Teacher request){
        Teacher teacher=teacherService.createNewTeacher(request);
        if(teacher ==null){
            log.error("Error creating new student");
            return ApiResponse.<Teacher>builder()
                    .code(ErrorCode.ERR_CREATE_TEACHER.getCode())
                    .message(ErrorCode.ERR_CREATE_TEACHER.getMessage())
                    .build();
        }
        else{
            return ApiResponse.<Teacher>builder() // tao Apiresponse tra ve
                    .code(1000)  // Success code (default)
                    .message("Course created successfully")
                    .result(teacher)
                    .build();
        }
    }
}
