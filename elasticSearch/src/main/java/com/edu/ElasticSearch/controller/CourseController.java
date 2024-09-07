package com.edu.ElasticSearch.controller;

import com.edu.ElasticSearch.dto.request.CreateCourseRequest;
import com.edu.ElasticSearch.dto.request.RemoveInforRequest;
import com.edu.ElasticSearch.dto.response.ApiResponse;
import com.edu.ElasticSearch.dto.response.CourseResponse;
import com.edu.ElasticSearch.entity.Course;
import com.edu.ElasticSearch.entity.InforCourse;
import com.edu.ElasticSearch.exception.ErrorCode;
import com.edu.ElasticSearch.services.CourseService;
import com.edu.ElasticSearch.services.InforCourseService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CourseController {
    @Autowired
    CourseService courseService;
    InforCourseService inforCourseService;
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
    @GetMapping("/getLevel")
    public List<CourseResponse> getCoursesByLevel(@RequestParam int level) {
        return courseService.getCoursesByLevel(level);
    }

    @GetMapping("/search/{text}")
    public List<Course> searchCourses(@PathVariable String text) {
        return courseService.searchCourses(text);
    }

    @PostMapping("/newInforCourse")
    public InforCourse createInforCourse(@RequestBody InforCourse request){
        return inforCourseService.newInforCourse((request));
    }
    @GetMapping("/getInforCourse")
    public InforCourse getInforCOurseByIdCourse(@RequestParam String id){
        return inforCourseService.getInforCourseByIdCourse(id);
    }
    @PostMapping("/removeInforCourse")
    public boolean removeInforCourse(@RequestBody RemoveInforRequest request){
        return inforCourseService.removeInforCourse(request.getCourseId());
    }
    @PostMapping("/modifyInforCourse")
    public ApiResponse<InforCourse> modifyInforCourse(@RequestBody InforCourse request){
        InforCourse one=inforCourseService.modify(request);
        if(one!=null){
            return ApiResponse.<InforCourse>builder() // tao Apiresponse tra ve
                    .code(1000)  // Success code (default)
                    .message("Course created successfully")
                    .result(one)
                    .build();
        }
        return ApiResponse.<InforCourse>builder()
                .code(ErrorCode.ERR_MODIFY_INFOR_COURSE.getCode())
                .message(ErrorCode.ERR_MODIFY_INFOR_COURSE.getMessage())
                .build();
    }
}
