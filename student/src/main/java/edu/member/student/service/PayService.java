package edu.member.student.service;

import com.google.zxing.WriterException;
import edu.member.student.dto.request.*;
import edu.member.student.dto.response.*;
import edu.member.student.entity.AccountPay;
import edu.member.student.entity.RegisterCourse;
import edu.member.student.entity.VerifyCode;
import edu.member.student.exception.ErrorCode;
import edu.member.student.repository.PayRepository;
import edu.member.student.repository.RegisterRepository;
import edu.member.student.repository.VerifyCodeRepository;
import edu.member.student.repository.httpClients.IdentityClient;
import edu.member.student.repository.httpClients.TokenClient;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PayService {
    @Autowired
    PayRepository payRepository;
    @Autowired
    TokenClient tokenClient;
    @Autowired
    IdentityClient identityClient;
    RegisterRepository registerRepository;
    @NonFinal
    @Value("${jwt.signerKey}")
    private String secretKey;
    EmailService emailService;
    VerifyCodeRepository verifyCodeRepository;
 
// ham tao mot tai khoan moii trong mang blockchain
    public AccountPay CreateAccountPay( @RequestHeader("Authorization") String authorizationHeader){
        String token = authorizationHeader.substring(7);
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey.getBytes())
                .parseClaimsJws(token)
                .getBody();
        String email = claims.getSubject(); // lay username trong token (username la email)
        if(payRepository.findByEmail(email).isPresent()){
            return null;
        }
        try{
            GetAccountPayResponse addAndKey=  tokenClient.getAccount(authorizationHeader);
            AccountPay newAcc= AccountPay.builder()
                    .address(addAndKey.getAddress())
                    .private_key(addAndKey.getPrivateKey())
                    .email(email)
                    .build();
            return payRepository.save(newAcc);
        }
        catch (Exception e){
            log.info(e.toString());
            return null;
        }
    }
    // kiem tra thong tin dang nhap
    public ApiResponse<AccountPayRespone> loginAccountPay(AuthenticationRequest request){ // kiem tra xem tai khoan da kich hoat token hay chua
        boolean authen=identityClient.authenPass(request).getResult();
        if (!authen){
            return ApiResponse.<AccountPayRespone>builder()
                    .code(ErrorCode.UNAUTHENTICATED.getCode())
                    .message(ErrorCode.UNAUTHORIZED.getMessage())
                    .result(null)
                    .build();
        }

        if(payRepository.findByEmail(request.getUsername()).isEmpty()){
            return ApiResponse.<AccountPayRespone>builder()
                    .code(ErrorCode.ERR_PAY_ACC_NOT_EXIST.getCode())
                    .message(ErrorCode.ERR_PAY_ACC_NOT_EXIST.getMessage())
                    .result(null)
                    .build();
        }
        AccountPay accountPay=payRepository.findByEmail(request.getUsername()).get();
        tokenClient.addKey(AddKeyRequest.builder().Key(accountPay.getPrivate_key()
        ).build());
        return ApiResponse.<AccountPayRespone>builder()
                .code(1000)
                .message("OK").result(
                        AccountPayRespone.builder()
                                .id(accountPay.getId())
                                .email(accountPay.getEmail())
                                .address(accountPay.getAddress())
                                .balance(tokenClient.getToken(accountPay.getAddress()))
                                .build()
                )
                .build();
    }



// tao ma QR code đe xac thuc
    public String generateQrCode(GenQRRequest data) throws IOException, WriterException {
        if(!verifyCodeRepository.findByEmail(data.getEmail()).isEmpty()){
            for(Optional<VerifyCode> e: verifyCodeRepository.findByEmail(data.getEmail())){
                verifyCodeRepository.delete(e.get());
            }
        }
        VerifyCode code=verifyCodeRepository.save(emailService.generateCode(data.getEmail()));
        return QRCodeGenerator.generateQRCodeBase64(code.getCode());
    }
// nap token vao he thong
    public ApiResponse<TransRespone> deposit(TransTokenRequest request){

        try{

             TransRespone res=tokenClient.transferToken(TransTokenRequest.builder()
                            .amount(request.getAmount())
                            .email(payRepository.findByEmail(request.getEmail()).get().getAddress())
                    .build());
             System.out.println("so du :  "+res.getResult());
            if(res.getResult()==0){
                return  ApiResponse.<TransRespone>builder()
                        .code(500)
                        .message("error in server")
                        .result(res)
                        .build();
            }
            return ApiResponse.<TransRespone>builder()
                    .code(1000)
                    .message("OK")
                    .result(res)
                    .build();
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
            return ApiResponse.<TransRespone>builder()
                    .code(500)
                    .message("Server error")
                    .result(null)
                    .build();
        }
    }
// mua khoa hoc
    public ApiResponse<BuyCourseResponse> buyCourse(BuyCourseRequest request){
        try{

            TransTokenRequest transTokenRequest=TransTokenRequest.builder()
                    .amount(request.getPrice())
                    .email(payRepository.findByEmail(request.getEmail()).get().getAddress())
                    .build();
            TransRespone res=tokenClient.buyCourse(transTokenRequest);
            if(res.getResult()==0){
                return  ApiResponse.<BuyCourseResponse>builder()
                        .code(ErrorCode.ERR_PAY.getCode())
                        .message(ErrorCode.ERR_PAY.getMessage())
                        .build();
            }
            if(res.getResult()==-1){
                return  ApiResponse.<BuyCourseResponse>builder()
                        .code(ErrorCode.ERR_BALANCE_NOT_ENOUGH.getCode())
                        .message(ErrorCode.ERR_BALANCE_NOT_ENOUGH.getMessage())
                        .build();
            }

            RegisterCourse newRegis= registerRepository.save(RegisterCourse.builder()
                    .email(request.getEmail())
                    .course(request.getCourse())
                    .time(LocalDateTime.now())
                    .build());
            return ApiResponse.<BuyCourseResponse>builder()
                    .code(1000)
                    .message("OK")
                    .result(BuyCourseResponse.builder()
                            .course(request.getCourse())
                            .email(request.getEmail())
                            .price(request.getPrice())
                            .time(LocalDateTime.now())
                            .id(newRegis.getId())
                            .build())
                    .build();
        }
        catch (Exception e){
            return ApiResponse.<BuyCourseResponse>builder()
                    .code(500)
                    .message("Không thể hoàn thành giao dịch")
                    .result(null)
                    .build();
        }
    }
}
