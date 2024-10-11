package edu.member.student.repository.httpClients;

import edu.member.student.dto.response.Course;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@FeignClient(name="course-service", url="http://127.0.0.1:8082/elasticSearch")

public interface CourseClient {
    @GetMapping(value = "/course/findallbyid")
    public Optional<Course> getYourCourse(@RequestParam String id); // id cua khoa hoc

}
