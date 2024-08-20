package edu.member.student.controller;

import edu.member.student.dto.request.SendEmailRequest;
import edu.member.student.dto.request.VerifyCodeRequest;
import edu.member.student.dto.response.ApiResponse;
import edu.member.student.dto.response.EmailSendResponse;
import edu.member.student.dto.response.StudentResponse;
import edu.member.student.exception.ErrorCode;
import edu.member.student.service.EmailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @PostMapping("/getMail")
    public String getmail(@RequestBody VerifyCodeRequest request){
        return emailService.getMail(request);
    }



}
