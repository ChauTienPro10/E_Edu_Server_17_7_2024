package com.edu.ElasticSearch.services;

import com.edu.ElasticSearch.dto.request.CreateCourseRequest;
import com.edu.ElasticSearch.dto.response.*;
import com.edu.ElasticSearch.entity.Course;
import com.edu.ElasticSearch.entity.InforCourse;
import com.edu.ElasticSearch.entity.Teacher;
import com.edu.ElasticSearch.exception.ErrorCode;
import com.edu.ElasticSearch.mapper.CourseMapper;
import com.edu.ElasticSearch.repository.CourseRepository;
import com.edu.ElasticSearch.repository.InforCourseRepository;
import com.edu.ElasticSearch.repository.SubjectRepository;
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
    InforCourseRepository inforCourseRepository;
    @Autowired
    CourseMapper courseMapper;
    TeacherRepository teacherRepository;
    SubjectRepository subjectRepository;
    public ApiResponse<Course> createCourse(CreateCourseRequest request){
        try{

            Course course= courseMapper.toCourse(request);
            if(courseRepository.findByTitle(course.getTitle()).isPresent()){
                return ApiResponse.<Course>builder()
                        .code(ErrorCode.ERR_COURSE_EXIST.getCode())
                        .message(ErrorCode.ERR_COURSE_EXIST.getMessage())
                        .result(null)
                        .build();
            }
            if(course.getLevel()<1 || course.getLevel()>4){
                return ApiResponse.<Course>builder()
                        .code(ErrorCode.ERR_COURSE_LEVEL_INVALID.getCode())
                        .message(ErrorCode.ERR_COURSE_LEVEL_INVALID.getMessage())
                        .result(null)
                        .build();
            }
            if(teacherRepository.findById(course.getTeacher()).isEmpty()){

                return ApiResponse.<Course>builder()
                        .code(ErrorCode.ERR_COURSE_TEACHER_INVALID.getCode())
                        .message(ErrorCode.ERR_COURSE_TEACHER_INVALID.getMessage())
                        .result(null)
                        .build();
            }
            if(teacherRepository.findById(course.getTeacher()).isPresent()
                && teacherRepository.findById(course.getTeacher()).get().getLevel()!=course.getLevel()
            ){
                return ApiResponse.<Course>builder()
                        .code(ErrorCode.ERR_COURSE_LEVEL_TEACHER_INVALID.getCode())
                        .message(ErrorCode.ERR_COURSE_LEVEL_TEACHER_INVALID.getMessage())
                        .result(null)
                        .build();
            }
            if(subjectRepository.findByCode(course.getSubject()).isEmpty()){
                return ApiResponse.<Course>builder()
                        .code(ErrorCode.ERR_COURSE_SUBJECT_INVALID.getCode())
                        .message(ErrorCode.ERR_COURSE_SUBJECT_INVALID.getMessage())
                        .result(null)
                        .build();
            }
            Course newCourse=courseRepository.save(course);
            InforCourse inforCourse=InforCourse.builder()
                    .course(newCourse.getId())
                    .descripContent(newCourse.getDescription())
                    .methods(new ArrayList<>())
                    .requires(new ArrayList<>())
                    .build();
            inforCourseRepository.save(inforCourse);// them thong tin khoa hoc
            return ApiResponse.<Course>builder()
                    .code(1000)
                    .message("OK")
                    .result(courseRepository.save(course))
                    .build();
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

    public List<Course> getAllCOurse() {
        List<Course> courses=  courseRepository.findAll();
//        List<CourseResponse> respone=new ArrayList<CourseResponse>();
//        for(Course course : courses){
//            log.info("teacher id",course.getTeacher());
//            Optional<Teacher> teacher=teacherRepository.findById(course.getTeacher());
//            CourseResponse courseResponse=CourseResponse.builder().id(Optional.ofNullable(course.getId()).orElse(null))
//                    .title(course.getTitle())
//                    .duration(course.getDuration())
//                    .level(course.getLevel())
//                    .price(course.getPrice())
//                    .description(course.getDescription())
//                    .teacher(teacher.get().getName())
//                    .build();
//            respone.add(courseResponse);
//        }
        return courses;

    }

    public List<Course> searchCourses(String text) {
        List<Course> list=courseRepository.searchByQueryString(text);
        for(Course it:list){
            it.setTeacher(teacherRepository.findById(it.getTeacher()).get().getName());
        }
        return list;
    }
    public List<Course> searchCourses_matcher(String text) {

        List<Course> list=courseRepository.searchByQueryString_matcher(text);
        for(Course it:list){
            it.setTeacher(teacherRepository.findById(it.getTeacher()).get().getName());
        }
        return list;
    }

    // lay tat ca khoa hoc theo id

    public Optional<Course> getAllByID_course(String id){
        Optional<Course> course= courseRepository.findById(id);
        course.get().setTeacher(teacherRepository.findById(course.get().getTeacher()).get().getName());
        return course;
    }
    public ApiResponse<List<Course>> findCourseByTeacherID(String email){

        List<Course> courses=courseRepository.findByTeacher(
                teacherRepository.findByEmail(email).get().getId()
        );
        if(courses.isEmpty()){
            return ApiResponse.<List<Course>>builder()
                    .code(30000)
                    .message("Bạn không có liên kết nào")
                    .build();
        }
        else{
            return ApiResponse.<List<Course>>builder()
                    .code(1000)
                    .message("OK")
                    .result(courses)
                    .build();
        }
    }

    public Management getInforTeacherAndCourse(){
        return Management.builder()
                .numOfTeacher(teacherRepository.count())
                .numOfCourse(courseRepository.count())
                .typeofTeacher(Typeof_teacher.builder()
                        .thpt(teacherRepository.countByLevel(3))
                        .thcs(teacherRepository.countByLevel(2))
                        .th(teacherRepository.countByLevel(1))
                        .build())
                .typesOfCourses(TypesOfCourses.builder()
                        .thpt(courseRepository.countByLevel(3))
                        .thcs(courseRepository.countByLevel(2))
                        .th(courseRepository.countByLevel(1))
                        .build())
                .build();
    }



}
