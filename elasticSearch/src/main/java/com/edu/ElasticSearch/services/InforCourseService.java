package com.edu.ElasticSearch.services;

import com.edu.ElasticSearch.entity.InforCourse;
import com.edu.ElasticSearch.repository.InforCourseRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class InforCourseService {
    InforCourseRepository inforCourseRepository;
    public InforCourse newInforCourse(InforCourse request){
        return inforCourseRepository.save(request);
    }
}
