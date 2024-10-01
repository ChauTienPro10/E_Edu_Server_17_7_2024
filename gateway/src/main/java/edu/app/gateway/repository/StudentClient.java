package edu.app.gateway.repository;

import edu.app.gateway.dto.request.AuthenticationRequest;
import edu.app.gateway.dto.request.GenQRRequest;
import edu.app.gateway.dto.request.StudentCreationRequest;
import edu.app.gateway.dto.request.TransTokenRequest;
import edu.app.gateway.dto.response.AccountPayRespone;
import edu.app.gateway.dto.response.ApiResponse;
import edu.app.gateway.dto.response.StudentResponse;
import edu.app.gateway.dto.response.TransRespone;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Mono;

public interface StudentClient {
    @GetExchange (url = "/profile/testAutho")
    Mono<String> getTest(@RequestHeader("Authorization") String token);

    @PostExchange (url ="/pay/account.pay.check" )
    Mono<ApiResponse<AccountPayRespone>> loginAccountPay(@RequestHeader("Authorization") String token
    , @RequestBody AuthenticationRequest request);

    @PostExchange (url ="/profile/new" )
    Mono<ApiResponse<StudentResponse>> Signup(@RequestBody StudentCreationRequest request);

    @PostExchange(url = "/pay/generateQR")
    Mono<ApiResponse<String>> genQr(@RequestHeader("Authorization") String token, @RequestBody GenQRRequest request);

    @PostExchange(url = "/pay/deposit")
    Mono<ApiResponse<TransRespone>> deposit( @RequestBody TransTokenRequest request);
}
