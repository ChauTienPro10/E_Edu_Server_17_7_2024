package edu.app.gateway.repository;

import edu.app.gateway.dto.request.*;
import edu.app.gateway.dto.response.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CourseClient {
    @GetExchange (url = "/course/getLevel")
    Mono<List<CourseResponse>> getCoursesByLevel(@RequestParam int level);
    @GetExchange (url = "/course/get.all")
    Mono<List<CourseResponse>> getCourses();
    @PostExchange(url="/teacher/new",contentType = MediaType.APPLICATION_JSON_VALUE)
    Mono<ApiResponse<CreateTeacherResponse>> createNewTeacher(@RequestBody CreateTeacherRequest request);

    @PostExchange(url = "/course/subject/new",contentType = MediaType.APPLICATION_JSON_VALUE)
    Mono<ApiResponse<Subject>> newSub(@RequestBody Subject subject);

    @PostExchange(url = "/course/new", contentType = MediaType.APPLICATION_JSON_VALUE)
    Mono<ApiResponse<Course>> ExchangeNewCourse(@RequestBody CreateCourseRequest request);

    @GetExchange(url = "/teacher/getall")
    Mono<ApiResponse<List<Teacher>>> getAllTeacher();
    @GetExchange(url = "/teaacher/get.by.level")
    Mono<ApiResponse<List<Teacher>>> getByLevelTeacher(@RequestParam int level,@RequestParam String code);

    @PostExchange(url = "/course/modifyInforCourse")// sua thon tin khoa hoc
    Mono<ApiResponse<InforCourse>> modifyInfor_course(@RequestBody InforCourse request
            ,@RequestHeader("Authorization") String authorizationHeader);

    @GetExchange(url = "/course/getInforCourse")
    Mono<InforCourse> getInforCOurseByIdCourse(@RequestParam String id);

    // xu ly video
    @PostExchange(url = "/video/new")
    Mono<ApiResponse<Video>> createVideo(@RequestBody Video request
    ,@RequestHeader("Authorization") String authorizationHeader);

    @GetExchange(url = "/video/getListVideo")
    Mono<List<Video>> getListVideo(@RequestParam String idcourse);

    @GetExchange(url = "/course/teacher.get.course")
    Mono<ApiResponse<List<Course>>> findCourseByTeacherID(@RequestParam String email);

    @GetExchange(url="/course/search/{text}")
    Mono<List<Course>> searchCourses(@PathVariable String text);
    @GetExchange(url="/course/matcher_search/{text}")
    Mono<List<Course>> searchCourses_matcher(@PathVariable String text);

    @GetExchange(url = "/course/admin.findbyid")
    Mono<String> admin_findbyid(@RequestParam String id);

    @PostExchange(url = "/discount/new")
    ApiResponse<CreateDiscountResponse> new_discount(@RequestBody CreateDiscountRequest request);
}
