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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TeacherControker {
    TeacherService teacherService;
    @PostMapping("/new")
    public ApiResponse<Teacher> newTeacher(@RequestBody Teacher request){
        return  teacherService.createNewTeacher(request);
        }
    @GetMapping("/getall")
    public ApiResponse<List<Teacher>> teacherGetAll(){
        return teacherService.getAllTeacher();
    }
    @GetMapping("/get.by.level")
    public ApiResponse<List<Teacher>> getByLevel(@RequestParam int level, @RequestParam String code){
        return teacherService.find_by_level(level,code);
    }



}
