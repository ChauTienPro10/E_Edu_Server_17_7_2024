package edu.member.student.service;

import edu.member.student.dto.request.ReceiveDiscountRequest;
import edu.member.student.dto.response.ApiResponse;
import edu.member.student.dto.response.Discount;
import edu.member.student.entity.MyDiscount;
import edu.member.student.repository.MyDiscountRepository;
import edu.member.student.repository.StudentRepository;
import edu.member.student.repository.httpClients.CourseClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyDiscountService {
    @Autowired
    MyDiscountRepository myDiscountRepository;
    @Autowired
    CourseClient courseClient;
    @Autowired
    StudentRepository studentRepository;
    public ApiResponse<MyDiscount> receive_discount(ReceiveDiscountRequest request){
        try{
            Discount discount=courseClient.find_by_code(request.getCode());
            if(discount==null){
                return ApiResponse.<MyDiscount>builder()
                        .code(502)
                        .message("Mã của bạn không tồn tại")
                        .build();
            }
            if(myDiscountRepository.findByDiscountcode(request.getCode()).isPresent()){
                return ApiResponse.<MyDiscount>builder()
                        .code(501)
                        .message("Bạn đã có mã này rồi")
                        .build();
            }
            MyDiscount myDiscount= MyDiscount.builder()
                    .discountcode(request.getCode())
                    .student(request.getEmail())
                    .status(true)
                    .build();

            return ApiResponse.<MyDiscount>builder()
                    .code(1000)
                    .message("OK")
                    .result(myDiscountRepository.save(myDiscount))
                    .build();
        }
        catch(Exception e){
            return ApiResponse.<MyDiscount>builder()
                    .code(500)
                    .message("Lỗi xử lý từ server "+ e)
                    .build();
        }
    }
}
