package com.edu.ElasticSearch.services;

import com.edu.ElasticSearch.entity.InforCourse;
import com.edu.ElasticSearch.repository.InforCourseRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class InforCourseService {
    InforCourseRepository inforCourseRepository;
    public InforCourse newInforCourse(InforCourse request) {
        // Check if the course with the given ID already exists
        if (getInforCourseByIdCourse(request.getCourse())!=null) {
            // If course already exists, handle the situation (e.g., throw an exception or return null)
            return null; // Or throw a custom exception
        }

        // If the course doesn't exist, save the new course
        return inforCourseRepository.save(request);
    }

    public InforCourse getInforCourseByIdCourse(String id) {
        // Retrieve the course based on the course ID
        Optional<InforCourse> courseInfor = inforCourseRepository.findByCourse(id);
        if(courseInfor.isPresent()){
            return courseInfor.get();
        }
        return null;
    }
    public boolean removeInforCourse(String idCourse) {
        // Retrieve the course based on the course ID
        InforCourse courseInfor = getInforCourseByIdCourse(idCourse);
        log.info("id course", courseInfor.getCourse());

        if (courseInfor!=null) {
            // If the course exists, delete it
            inforCourseRepository.delete(courseInfor);
            return true; // Indicate successful deletion
        }

        // Return false if the course does not exist
        return false;
    }

    public InforCourse modify(InforCourse request){
        if(inforCourseRepository.findById(request.getId()).isPresent()){
            InforCourse oldOne=inforCourseRepository.findById(request.getId()).get();
            oldOne.setCourse(request.getCourse());
            oldOne.setMethods(request.getMethods());
            oldOne.setRequires(request.getRequires());
            oldOne.setDescripContent(request.getDescripContent());
            return inforCourseRepository.save(oldOne);
        }
        return null;
    }
}
