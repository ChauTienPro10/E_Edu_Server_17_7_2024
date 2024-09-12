package com.edu.ElasticSearch.services;

import com.edu.ElasticSearch.entity.Answer;
import com.edu.ElasticSearch.repository.AnswerRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AnswerService {
    AnswerRepository answerRepository;
    public Answer newAnswer(Answer ans){
        return answerRepository.save(ans);
    }
}
