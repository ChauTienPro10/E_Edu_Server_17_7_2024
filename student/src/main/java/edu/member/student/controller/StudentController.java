package edu.member.student.controller;

import edu.member.student.dto.request.StudentCreationRequest;
import edu.member.student.dto.response.ApiResponse;
import edu.member.student.dto.response.StudentResponse;
import edu.member.student.entity.Student;
import edu.member.student.repository.httpClients.IdentityClient;
import edu.member.student.service.StudentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentController {
    @Autowired
    StudentService studentService;
    IdentityClient identityClient;


    @PostMapping("/new")
    ApiResponse<StudentResponse> newStudent(@RequestBody StudentCreationRequest request){
       return  studentService.newStudent(request);
    }

    @GetMapping("/amount_of_student")
    public long get_amount_student(){
        return studentService.get_amount_student();
    }
    @GetMapping("/get_by_index")
    public List<Student> get_by_index(@RequestParam int index, @RequestParam int size){
        return studentService.getNext10Students(index,size);
    }




}
