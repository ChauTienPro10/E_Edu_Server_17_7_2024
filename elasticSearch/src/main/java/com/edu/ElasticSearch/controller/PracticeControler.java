package com.edu.ElasticSearch.controller;

import com.edu.ElasticSearch.dto.request.CreateNewPracticeRequest;
import com.edu.ElasticSearch.dto.response.ApiResponse;
import com.edu.ElasticSearch.entity.Practice;
import com.edu.ElasticSearch.services.PracticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/practice")
public class PracticeControler {
    @Autowired
    PracticeService practiceService;
    @PostMapping("/addnew")
    public ApiResponse<Practice> add(@RequestBody CreateNewPracticeRequest request) {
        return practiceService.CreateNewrequest(request);
    }
}
