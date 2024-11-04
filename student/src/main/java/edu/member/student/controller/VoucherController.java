package edu.member.student.controller;

import edu.member.student.dto.request.ClamVoucherRequest;
import edu.member.student.dto.response.Voucher;
import edu.member.student.entity.MyVoucher;
import edu.member.student.repository.httpClients.CourseClient;
import edu.member.student.service.MyVoucherService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/voucher")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class VoucherController {
    @Autowired
    MyVoucherService myVoucherService;

    @PostMapping("/clamVoucher")
    public MyVoucher clamVoucher(@RequestBody ClamVoucherRequest request){
        return myVoucherService.clamVoucher(request);
    }
}
