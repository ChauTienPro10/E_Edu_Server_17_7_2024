package com.edu.ElasticSearch.controller;

import com.edu.ElasticSearch.dto.request.CreateCourseRequest;
import com.edu.ElasticSearch.dto.response.ApiResponse;
import com.edu.ElasticSearch.entity.Course;
import com.edu.ElasticSearch.exception.ErrorCode;
import com.edu.ElasticSearch.services.CourseService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CourseController {
    @Autowired
    CourseService courseService;
    @PostMapping("/new")
    public ApiResponse<Course> newCourse(@RequestBody CreateCourseRequest request){
        Course course=courseService.createCourse(request);
        if(course==null){
            log.error("Error creating new student");
            return ApiResponse.<Course>builder()
                    .code(ErrorCode.ERR_CREATE_NEW_COURSE.getCode())
                    .message(ErrorCode.ERR_CREATE_NEW_COURSE.getMessage())
                    .build();
        }
        else{
            return ApiResponse.<Course>builder() // tao Apiresponse tra ve
                    .code(1000)  // Success code (default)
                    .message("Course created successfully")
                    .result(course)
                    .build();
        }
    }
}
