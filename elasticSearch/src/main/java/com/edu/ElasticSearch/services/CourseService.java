package com.edu.ElasticSearch.services;

import com.edu.ElasticSearch.dto.request.CreateCourseRequest;
import com.edu.ElasticSearch.entity.Course;
import com.edu.ElasticSearch.mapper.CourseMapper;
import com.edu.ElasticSearch.repository.CourseRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CourseService {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    CourseMapper courseMapper;
    public Course createCourse(CreateCourseRequest request){
        try{
            Course course= courseMapper.toCourse(request);
            courseRepository.save(course);
            return course;
        }
        catch (Exception ex){
            log.info(ex.toString());
            return null;
        }
    }
}
