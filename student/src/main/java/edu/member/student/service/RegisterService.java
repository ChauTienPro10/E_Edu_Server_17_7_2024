package edu.member.student.service;

import edu.member.student.dto.request.FetchInforRegisterRequest;
import edu.member.student.dto.response.ApiResponse;
import edu.member.student.dto.response.Course;
import edu.member.student.entity.RegisterCourse;
import edu.member.student.exception.ErrorCode;
import edu.member.student.repository.RegisterRepository;
import edu.member.student.repository.httpClients.CourseClient;
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
public class RegisterService {
    RegisterRepository registerRepository;
    @Autowired
    CourseClient courseClient;
    // kiểm tra học viên có dăng ký khóa hoọc hay chưa
    public ApiResponse<Boolean> registerCheck(FetchInforRegisterRequest request){
        try{
            Optional<RegisterCourse> inforCourse= registerRepository
                    .findByEmailAndCourse(request.getEmail(),request.getCourse());
            if(inforCourse.isEmpty()){
                return ApiResponse.<Boolean>builder()
                        .code(ErrorCode.ERROR_REGISTER_NOT_EXIST.getCode())
                        .message(ErrorCode.ERROR_REGISTER_NOT_EXIST.getMessage())
                        .result(false)
                        .build();
            }
            return ApiResponse.<Boolean>builder()
                    .code(1000)
                    .message("OK")
                    .result(true)
                    .build();
        }
        catch (Exception e){
            log.info(e.toString());
            return ApiResponse.<Boolean>builder()
                    .code(500)
                    .message("Lỗi xảy ra trong qu trình máy chủ xử lý: "+e.toString())
                    .result(false)
                    .build() ;
        }
    }

    public ApiResponse<List<Course>> getYourCourse(String email){
        List<Optional<RegisterCourse>> registers= registerRepository.findByEmail(email);
        if(registers.isEmpty()){
            System.out.println(email);
            return ApiResponse.<List<Course>>builder()
                    .code(ErrorCode.ERROR_REGISTER_NOT_EXIST.getCode())
                    .message(ErrorCode.ERROR_REGISTER_NOT_EXIST.getMessage())
                    .build();
        }
        else{
            List<Course> responses_Courses=new ArrayList<Course>(); 
            for (Optional<RegisterCourse> register: registers){
                System.out.println(register.get().getCourse());
                Optional<Course> it=courseClient.getYourCourse(register.get().getCourse());
                it.ifPresent(responses_Courses::add);
            }
            if(responses_Courses.isEmpty()){
                return ApiResponse.<List<Course>>builder()
                        .code(ErrorCode.ERROR_REGISTER_NOT_EXIST.getCode())
                        .message("Khong nhan duoc danh sach khoa hoc")
                        .build();
            }
            else{
                return ApiResponse.<List<Course>>builder()
                        .code(1000)
                        .message("OK")
                        .result(responses_Courses)
                        .build();
            }
        }
    }
}
