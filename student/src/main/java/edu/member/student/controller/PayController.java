package edu.member.student.controller;

import com.google.zxing.WriterException;
import edu.member.student.dto.request.*;
import edu.member.student.dto.response.*;
import edu.member.student.entity.AccountPay;
import edu.member.student.exception.ErrorCode;
import edu.member.student.service.PayService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/pay")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PayController {
    PayService payService;
    @PostMapping("/new")
    public ApiResponse<AccountPay> newAccountPay(@RequestHeader("Authorization") String authorizationHeader){
        AccountPay newAcc=payService.CreateAccountPay(authorizationHeader);

        if(newAcc==null){
            return ApiResponse.<AccountPay>builder()
                    .code(ErrorCode.ERR_CREATE_ACCOUNTPAY.getCode())
                    .message(ErrorCode.ERR_CREATE_ACCOUNTPAY.getMessage())
                    .result(null)
                    .build();
        }
        return ApiResponse.<AccountPay>builder()
                .code(1000)
                .message("create success!")
                .result(newAcc)
                .build();
    }
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/account.pay.check")
    public ApiResponse<AccountPayRespone> checkAccPAy(@RequestBody AuthenticationRequest request){
        return payService.loginAccountPay(request);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/generateQR")
    public ApiResponse<String> generateQRCode(@RequestBody GenQRRequest data) throws IOException, WriterException {
        return ApiResponse.<String>builder()
                .code(1000)
                .message("OK")
                .result(payService.generateQrCode(data))
                .build();
    }
    @PostMapping("/deposit")
    public ApiResponse<TransRespone> deposit(@RequestBody TransTokenRequest request){
        return payService.deposit(request);
    }
    @PostMapping("/buy.course")
    public ApiResponse<BuyCourseResponse> buyCourse(@RequestBody BuyCourseRequest request){
        return payService.buyCourse(request);
    }

}
