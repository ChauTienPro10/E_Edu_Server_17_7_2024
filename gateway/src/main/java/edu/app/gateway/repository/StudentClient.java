package edu.app.gateway.repository;

import edu.app.gateway.dto.request.*;
import edu.app.gateway.dto.response.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Mono;

import java.util.List;

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

    // check course register
    @PostExchange(url = "/register/check")
    Mono<ApiResponse<Boolean>> checkInforRegister(@RequestBody FetchInforRegisterRequest request);

    @GetExchange(url = "/comment/get.comment")
    Mono<List<CommentResponse>> getComment(@RequestParam String idcourse);

    @PostExchange(url = "/comment/delete.comment")
    Mono<Boolean> deleteCmt(@RequestBody DeleteCommentRequest request
            ,@RequestHeader("Authorization") String authorizationHeader);

    @GetExchange(url = "/register/getyourcourse")
    ApiResponse<Course> getYourCourse (@RequestParam String email);

    @GetExchange(url = "/comment/get.notify")
    List<Notify> getAll_notify();

    @GetExchange(url = "/manager/getInforManagement")
    ApiResponse<Management> getinforManagement();
    @GetExchange(url = "/profile/amount_of_student")
    long get_amount_student();
    @GetExchange(url = "/profile/get_by_index")
    List<Student> get_by_index(@RequestParam int index, @RequestParam int size);

    @GetExchange(url = "/profile/find_student")
    List<Student> find_student(@RequestParam String data);

}
