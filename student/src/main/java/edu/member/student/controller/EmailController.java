package edu.member.student.controller;

import edu.member.student.dto.request.SendEmailRequest;
import edu.member.student.dto.request.VerifyCodeRequest;
import edu.member.student.dto.response.ApiResponse;
import edu.member.student.dto.response.EmailSendResponse;
import edu.member.student.dto.response.StudentResponse;
import edu.member.student.entity.VerifyCode;
import edu.member.student.exception.ErrorCode;
import edu.member.student.service.EmailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailController {
    EmailService emailService;

    @PostMapping("/send")
    ApiResponse<EmailSendResponse> sendEMail(@RequestBody SendEmailRequest request){
        try{
            return ApiResponse.<EmailSendResponse>builder()
                    // tao Apiresponse tra ve
                    .code(1000)  // Success code (default)
                    .message("Student created successfully")
                    .result(emailService.sendSimpleEmail(request.getToEmail(),request.getTitle(),request.getContent()))
                    .build();
        }
        catch (Exception ex){
            log.info(ex.toString());
            return ApiResponse.<EmailSendResponse>builder()
                    .code(ErrorCode.ERROR_SEND_EMAIL.getCode())
                    .message(ErrorCode.ERROR_SEND_EMAIL.getMessage())
                    .build();
        }

    }



    @PostMapping("/newCode")
    public ApiResponse<VerifyCode> newCode(@RequestHeader("Authorization") String authorizationHeader){
        String token = authorizationHeader.startsWith("Bearer ") ? authorizationHeader.substring(7) : authorizationHeader;
        String email=emailService.getMail(token);
        VerifyCode newCode=  emailService.sendCode(email);
        if(newCode==null){
            return ApiResponse.<VerifyCode>builder()
                    .code(2001)
                    .message("cant send code to this email!")
                    .build();
        }
        else{
            return ApiResponse.<VerifyCode>builder()
                    .code(1000)
                    .message("Student created successfully")
                    .result(newCode)
                    .build();
        }
    }

    @PostMapping("/authenCode")
    public ApiResponse<Boolean> authenCode(@RequestBody VerifyCodeRequest request
            ,@RequestHeader("Authorization") String authorizationHeader){
        String token = authorizationHeader.startsWith("Bearer ") ? authorizationHeader.substring(7) : authorizationHeader;
        try{
            return ApiResponse.<Boolean>builder()
                    .code(1000)
                    .message("")
                    .result(emailService.verificationCode(request,token))
                    .build();
        }
        catch (Exception ex){
            log.info(ex.toString());
            return ApiResponse.<Boolean>builder()
                    .code(2001)
                    .message(ex.toString())
                    .result(false)
                    .build();
        }
    }



}
