package com.edu.ElasticSearch.services;

import com.edu.ElasticSearch.dto.request.CreateCourseRequest;
import com.edu.ElasticSearch.dto.response.CourseResponse;
import com.edu.ElasticSearch.entity.Course;
import com.edu.ElasticSearch.entity.Teacher;
import com.edu.ElasticSearch.mapper.CourseMapper;
import com.edu.ElasticSearch.repository.CourseRepository;
import com.edu.ElasticSearch.repository.TeacherRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CourseService {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    CourseMapper courseMapper;
    TeacherRepository teacherRepository;
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
    public List<CourseResponse> getCoursesByLevel(int level) {
        List<Course> courses=courseRepository.findByLevel(level);
        List<CourseResponse> respone=new ArrayList<CourseResponse>();
        for(Course course : courses){
            log.info("teacher id",course.getTeacher());
            Optional<Teacher> teacher=teacherRepository.findById(course.getTeacher());
            CourseResponse courseResponse=CourseResponse.builder().id(course.getId())
                    .title(course.getTitle())
                    .duration(course.getDuration())
                    .level(course.getLevel())
                    .price(course.getPrice())
                    .description(course.getDescription())
                    .teacher(teacher.get().getName())
                    .build();
            respone.add(courseResponse);
        }
        return respone;

    }

    public List<Course> searchCourses(String text) {
        return courseRepository.searchByQueryString(text);
    }
}
