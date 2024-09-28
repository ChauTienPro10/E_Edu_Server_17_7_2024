package com.edu.app.controllers;


import com.edu.app.dto.request.AuthenticationRequest;
import com.edu.app.dto.request.IntrospectRequest;
import com.edu.app.dto.request.LogoutRequest;
import com.edu.app.dto.response.ApiResponse;
import com.edu.app.dto.response.AuthenticationResponse;
import com.edu.app.dto.response.IntrospectResponse;
import com.edu.app.exception.ErrorCode;
import com.edu.app.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class IdentityController {
    AuthenticationService authenticationService;
    @GetMapping("/hl")
    public String hello() {
        return "Hello World";
    }
    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request) {

        var result = authenticationService.introspect(request);
        return ApiResponse.<IntrospectResponse>builder().result(result).build();
    }
    @PostMapping("/token")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        var result = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder().result(result).build();

    }
    @PostMapping("/logout")
    ApiResponse<Void> logout(@RequestBody LogoutRequest request) throws ParseException, JOSEException {
        authenticationService.logout(request);
        return ApiResponse.<Void>builder().build();
    }
    @PostMapping("/wallet.authen.password")
    public ApiResponse<Boolean> authenPassword(@RequestBody AuthenticationRequest request){
        try{
            return ApiResponse.<Boolean>builder()
                    .code(1000) //xac thuc thanh cong
                    .result(authenticationService.authenPass(request)).build();
        }
        catch (Exception e){
            log.info(e.toString());
            return ApiResponse.<Boolean>builder()
                    .code(ErrorCode.UNAUTHENTICATED.getCode())
                    .result(false).build();
        }
    }

}
