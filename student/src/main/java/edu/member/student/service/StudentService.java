package edu.member.student.service;

import edu.member.student.dto.request.StudentCreationRequest;
import edu.member.student.dto.request.UserCreateRequest;
import edu.member.student.dto.response.ApiResponse;
import edu.member.student.dto.response.StudentResponse;
import edu.member.student.entity.Student;
import edu.member.student.exception.ErrorCode;
import edu.member.student.exception.StudentErr;
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

import java.util.regex.Pattern;

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
        if(!isValidPhoneNumber(request.getPhone())){
            log.info("phone err: ","phone khong hop le");
            return ApiResponse.<StudentResponse>builder()
                    .code(StudentErr.PHONE_INVALID.getCode())
                    .message(StudentErr.PHONE_INVALID.getMessage())
                    .build();
        }
        if(!isValidEmail(request.getEmail())){
            log.info("email khong hop le");
            return ApiResponse.<StudentResponse>builder()
                    .code(StudentErr.EMAIL_INVALID.getCode())
                    .message(StudentErr.EMAIL_INVALID.getMessage())
                    .build();
        }
        if(!isValidPassword(request.getPassword())){
            log.info("password khong hop le");
            return ApiResponse.<StudentResponse>builder()
                    .code(StudentErr.PASSWORD_INVALID.getCode())
                    .message(StudentErr.PASSWORD_INVALID.getMessage())
                    .build();
        }
        if(studentRepository.findByEmail(request.getEmail())!=null){
            return ApiResponse.<StudentResponse>builder()
                    .code(2000)
                    .message("This email has been use!")
                    .build();
        }
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

    public static boolean isValidPhoneNumber(String phoneNumber) {// kiem tra so dien thoai co hop le hay kh
        // Biểu thức chính quy cho số điện thoại
        String regex = "^(\\+\\d{1,3}[- ]?)?\\d{10}$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(phoneNumber).matches();
    }

    public static boolean isValidEmail(String email) {// kuem tra emial hop le hay khong
        // Biểu thức chính quy cho email
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(email).matches();
    }

    public static boolean isValidPassword(String password) {// kiem tra an toan mat khau
        // Biểu thức chính quy để phát hiện các ký tự nguy hiểm tiềm ẩn cho SQL Injection
        String dangerousChars = "['\";=--/*\\\\]";
        Pattern pattern = Pattern.compile(dangerousChars);

        // Kiểm tra xem mật khẩu có chứa các ký tự nguy hiểm không
        return !pattern.matcher(password).find();
    }
}
