package edu.member.student.service;

import edu.member.student.dto.request.StudentCreationRequest;
import edu.member.student.dto.request.UserCreateRequest;
import edu.member.student.dto.response.ApiResponse;
import edu.member.student.dto.response.StudentResponse;
import edu.member.student.entity.Student;
import edu.member.student.exception.ErrorCode;
import edu.member.student.mapper.StudentMapper;


import edu.member.student.mapper.UserIdentityMapper;
import edu.member.student.repository.StudentRepository;
import edu.member.student.repository.httpClients.IdentityClient;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class StudentService {

    StudentRepository studentRepository;
    @Autowired
    StudentMapper studentMapper;
    IdentityClient identityClient;
    UserIdentityMapper userIdentityMapper;

    public ApiResponse<StudentResponse>  newStudent(StudentCreationRequest request){
        try{
            request.setFullname(request.fullnameUper(request.getFullname()));
            Student newStd=studentMapper.toStudent(request);// chuyen yeu cau ve student
            newStd=studentRepository.save(newStd);// luu student
            UserCreateRequest newUser=userIdentityMapper.toUserCreateRequest(request);
            newUser.setUsername(newStd.getEmail());
            newUser.setMemberId(newStd.getId());
//        System.out.println(newUser.getPassword());
            identityClient.createNewAccount(newUser);
            return ApiResponse.<StudentResponse>builder() // tao Apiresponse tra ve
                    .code(1000)  // Success code (default)
                    .message("Student created successfully")
                    .result(studentMapper.toStudentResponse(newStd))
                    .build();
        }
        catch(Exception ex){
            log.error("Error creating new student", ex);
            return ApiResponse.<StudentResponse>builder()
                    .code(ErrorCode.ERROR_CREATE_NEW_STUDENT.getCode())
                    .message(ErrorCode.ERROR_CREATE_NEW_STUDENT.getMessage())
                    .build();
        }

    }
}
