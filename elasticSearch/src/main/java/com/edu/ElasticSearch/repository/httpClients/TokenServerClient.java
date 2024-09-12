package com.edu.ElasticSearch.repository.httpClients;


import com.edu.ElasticSearch.dto.request.GetAccountPayRequest;
import com.edu.ElasticSearch.dto.response.GetAccountPayResponse;
import com.edu.ElasticSearch.entity.AccountPay;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name="toke-service", url="http://127.0.0.1:3001")
public interface TokenServerClient {
    @GetMapping(value = "/registerService/hello",produces = MediaType.APPLICATION_JSON_VALUE)
    String hello();
    @PostMapping(value = "/registerService/getNewAccountPay",produces = MediaType.APPLICATION_JSON_VALUE)
    GetAccountPayResponse getAccount(GetAccountPayRequest verCode);
}
