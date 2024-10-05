package edu.member.student.service;

import edu.member.student.dto.request.FetchInforRegisterRequest;
import edu.member.student.dto.response.ApiResponse;
import edu.member.student.entity.RegisterCourse;
import edu.member.student.exception.ErrorCode;
import edu.member.student.repository.RegisterRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RegisterService {
    RegisterRepository registerRepository;

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

}
