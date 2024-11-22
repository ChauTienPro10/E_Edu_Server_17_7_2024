package edu.member.student.repository.httpClients;

import edu.member.student.dto.response.Course;
import edu.member.student.dto.response.Discount;
import edu.member.student.dto.response.Management;
import edu.member.student.dto.response.Voucher;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@FeignClient(name="course-service", url="http://${COURSE_URL:127.0.0.1}:8082/elasticSearch")

public interface CourseClient {
    @GetMapping(value = "/course/findallbyid")
    public Optional<Course> getYourCourse(@RequestParam String id); // id cua khoa hoc
    @GetMapping(value = "/course/getNumOfTeacherAndCourses")
    Management getNumOfTeacherAndCourses();
    @GetMapping(value = "/discount/find_by_code")
    Discount find_by_code(@RequestParam("code") String code);

    @GetMapping(value = "/voucher/findByCode")
    Voucher findByCodeOfVoucher(@RequestParam String code);
}
