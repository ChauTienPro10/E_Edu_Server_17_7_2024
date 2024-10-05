package edu.member.student.repository.httpClients;

import edu.member.student.dto.request.AddKeyRequest;
import edu.member.student.dto.request.BuyCourseRequest;
import edu.member.student.dto.request.TransTokenRequest;
import edu.member.student.dto.response.ApiResponse;
import edu.member.student.dto.response.GetAccountPayResponse;
import edu.member.student.dto.response.TransRespone;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@FeignClient(name = "${token.name}", url = "${token.host}")
public interface TokenClient {
    @PostMapping(value = "/registerService/getNewAccountPay",produces = MediaType.APPLICATION_JSON_VALUE)
    GetAccountPayResponse getAccount( @RequestHeader("Authorization") String authorizationHeader);
    @GetMapping(value = "/registerService/getbalance",produces =MediaType.APPLICATION_JSON_VALUE)
    double getToken(@RequestParam String address);

    @PostMapping(value = "/registerService/trans.token",produces =MediaType.APPLICATION_JSON_VALUE)
    TransRespone transferToken(@RequestBody TransTokenRequest request);
    @PostMapping(value = "/registerService/pay.token",produces =MediaType.APPLICATION_JSON_VALUE)
    TransRespone buyCourse(@RequestBody TransTokenRequest request);

    @PostMapping(value = "/registerService/add.key")
    boolean addKey(@RequestBody AddKeyRequest request);
}
