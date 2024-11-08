package com.edu.ElasticSearch.controller;

import com.edu.ElasticSearch.dto.request.CreateNewPracticeRequest;
import com.edu.ElasticSearch.dto.response.ApiResponse;
import com.edu.ElasticSearch.entity.Practice;
import com.edu.ElasticSearch.services.PracticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/practice")
public class PracticeControler {
    @Autowired
    PracticeService practiceService;
    @PostMapping("/addnew")
    public ApiResponse<Practice> add(@RequestBody CreateNewPracticeRequest request) {
        return practiceService.CreateNewPractice(request);
    }
    @GetMapping("/getAllCourseById")
    public Optional<List<Practice>> getAll(@RequestParam String id){
        return practiceService.findAllByIdOfCourse(id);
    }
}
