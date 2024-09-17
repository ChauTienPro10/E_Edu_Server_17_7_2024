package edu.member.student.controller;

import edu.member.student.dto.request.GetAccountPayRequest;
import edu.member.student.dto.response.ApiResponse;
import edu.member.student.dto.response.GetAccountPayResponse;
import edu.member.student.entity.AccountPay;
import edu.member.student.exception.ErrorCode;
import edu.member.student.service.PayService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

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
}
