package com.edu.ElasticSearch.controller;

import com.edu.ElasticSearch.entity.Answer;
import com.edu.ElasticSearch.services.AnswerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/answer")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AnswerControler {
    AnswerService answerService;
    @PostMapping("/new")
    public Answer newANs(@RequestBody Answer request){
        return answerService.newAnswer(request);
    } 
}
