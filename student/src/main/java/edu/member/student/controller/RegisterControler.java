package edu.member.student.controller;

import edu.member.student.dto.request.FetchInforRegisterRequest;
import edu.member.student.dto.response.ApiResponse;
import edu.member.student.dto.response.Course;
import edu.member.student.service.RegisterService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RegisterControler {

    RegisterService registerService;

    @PostMapping("/check")
    public ApiResponse<Boolean> checkInforRegister(@RequestBody FetchInforRegisterRequest request){
        return registerService.registerCheck(request);
    }

    @GetMapping("/getyourcourse")
    public ApiResponse<List<Course>> getYourCourse(@RequestParam String email){
        return registerService.getYourCourse(email);
    }
}
