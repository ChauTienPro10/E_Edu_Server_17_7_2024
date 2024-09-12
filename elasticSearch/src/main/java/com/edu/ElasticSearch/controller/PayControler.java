package com.edu.ElasticSearch.controller;

import com.edu.ElasticSearch.dto.request.GetAccountPayRequest;
import com.edu.ElasticSearch.entity.AccountPay;
import com.edu.ElasticSearch.services.PayService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pay")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PayControler {
    PayService payService;
    @GetMapping("/test")
    public String test(){
        return payService.helo();
    }
    @PostMapping("/getAccountPay")
    public AccountPay getNewAccountPay(@RequestBody GetAccountPayRequest request){
        return payService.newAccountPay(request);
    }

}
