package com.edu.ElasticSearch.services;

import com.edu.ElasticSearch.dto.response.ApiResponse;
import com.edu.ElasticSearch.entity.InforCourse;
import com.edu.ElasticSearch.entity.Video;
import com.edu.ElasticSearch.repository.CourseRepository;
import com.edu.ElasticSearch.repository.InforCourseRepository;
import com.edu.ElasticSearch.repository.TeacherRepository;
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
    TeacherRepository teacherRepository;
    CourseRepository courseRepository;
    InforCourseRepository inforCourseRepository;
    public InforCourse newInforCourse(InforCourse request) {

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

    public InforCourse modify(InforCourse request,String email){
        if(!teacherRepository.findByEmail(email).get().getId()
                .equals(courseRepository.findById(request.getCourse()).get().getTeacher())){
            return null;
        }
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
