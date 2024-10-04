package edu.app.gateway.repository;

import edu.app.gateway.dto.request.*;
import edu.app.gateway.dto.response.*;
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
    @PostExchange(url = "/pay/buy.course")
    Mono<ApiResponse<BuyCourseResponse>> buyCourse(@RequestBody BuyCourseRequest request);

    // authentication
    @PostExchange(url = "/email/authenCode")
    ApiResponse<Boolean> authenCode(@RequestBody VerifyCodeRequest request
            ,@RequestHeader("Authorization") String authorizationHeader) ;
}
