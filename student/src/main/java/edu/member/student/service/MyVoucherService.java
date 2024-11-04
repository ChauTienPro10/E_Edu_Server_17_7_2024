package edu.member.student.service;

import edu.member.student.dto.request.ClamVoucherRequest;
import edu.member.student.dto.request.ReceiveDiscountRequest;
import edu.member.student.dto.response.ApiResponse;
import edu.member.student.dto.response.Discount;

import edu.member.student.dto.response.Voucher;
import edu.member.student.entity.MyVoucher;
import edu.member.student.entity.Student;
import edu.member.student.repository.MyVoucherRepository;
import edu.member.student.repository.StudentRepository;
import edu.member.student.repository.httpClients.CourseClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyVoucherService {
    @Autowired
    CourseClient courseClient;
    @Autowired
    MyVoucherRepository myVoucherRepository;

    @Autowired StudentRepository studentRepository;
    public MyVoucher clamVoucher(ClamVoucherRequest request){
        Voucher voucher=courseClient.findByCodeOfVoucher(request.getVoucherId());
        if(voucher == null){
            return null;
        }
        Student student =studentRepository.findByEmail(request.getStudentEmail());
        if(student==null){return null;}
        MyVoucher myVoucher= MyVoucher.builder()
                .voucherId(request.getVoucherId())
                .studentEmail(request.getStudentEmail())
                .used(false)
                .build();
        return myVoucherRepository.save(myVoucher);
    }

}
