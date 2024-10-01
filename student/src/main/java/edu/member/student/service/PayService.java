package edu.member.student.service;

import com.google.zxing.WriterException;
import edu.member.student.dto.request.*;
import edu.member.student.dto.response.AccountPayRespone;
import edu.member.student.dto.response.ApiResponse;
import edu.member.student.dto.response.GetAccountPayResponse;
import edu.member.student.dto.response.TransRespone;
import edu.member.student.entity.AccountPay;
import edu.member.student.exception.ErrorCode;
import edu.member.student.repository.PayRepository;
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
    @NonFinal
    @Value("${jwt.signerKey}")
    private String secretKey;

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




    public String generateQrCode(GenQRRequest data) throws IOException, WriterException {
        return QRCodeGenerator.generateQRCodeBase64(data.getData());
    }

    public ApiResponse<TransRespone> deposit(TransTokenRequest request){

        try{

             TransRespone res=tokenClient.transferToken(TransTokenRequest.builder()
                            .amount(request.getAmount())
                            .email(payRepository.findByEmail(request.getEmail()).get().getAddress())
                    .build());
            if(res.getResult().equals("ERROR")){
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
            return ApiResponse.<TransRespone>builder()
                    .code(500)
                    .message("Server error")
                    .result(null)
                    .build();
        }
    }
}
