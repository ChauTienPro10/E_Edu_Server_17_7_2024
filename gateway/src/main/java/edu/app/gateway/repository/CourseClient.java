package edu.app.gateway.repository;

import edu.app.gateway.dto.request.CreateTeacherRequest;
import edu.app.gateway.dto.request.IntrospectRequest;
import edu.app.gateway.dto.request.Subject;
import edu.app.gateway.dto.response.ApiResponse;
import edu.app.gateway.dto.response.CourseResponse;
import edu.app.gateway.dto.response.CreateTeacherResponse;
import edu.app.gateway.dto.response.IntrospectResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
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
}
