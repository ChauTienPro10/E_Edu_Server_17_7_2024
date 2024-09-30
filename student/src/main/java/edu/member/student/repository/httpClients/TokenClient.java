package edu.member.student.repository.httpClients;

import edu.member.student.dto.response.GetAccountPayResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "${token.name}", url = "${token.host}")
public interface TokenClient {
    @PostMapping(value = "/registerService/getNewAccountPay",produces = MediaType.APPLICATION_JSON_VALUE)
    GetAccountPayResponse getAccount( @RequestHeader("Authorization") String authorizationHeader);
    @GetMapping(value = "/registerService/getbalance",produces =MediaType.APPLICATION_JSON_VALUE)
    double getToken(@RequestParam String address);
}
