package edu.member.student.controller;

import edu.member.student.dto.request.StudentCreationRequest;
import edu.member.student.dto.response.ApiResponse;
import edu.member.student.dto.response.StudentResponse;
import edu.member.student.repository.httpClients.IdentityClient;
import edu.member.student.service.StudentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentController {
    @Autowired
    StudentService studentService;
    IdentityClient identityClient;
    @GetMapping("/test")
    public String TestApi(){
        return identityClient.hello();
    }
    @PostMapping("/new")
    ApiResponse<StudentResponse> newStudent(@RequestBody StudentCreationRequest request){
       return  studentService.newStudent(request);
    }



}
