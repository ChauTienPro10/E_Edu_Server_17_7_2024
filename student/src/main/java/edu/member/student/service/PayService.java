package edu.member.student.service;

import edu.member.student.dto.request.AuthenticationRequest;
import edu.member.student.dto.request.CheckAccPAy;
import edu.member.student.dto.request.GetAccountPayRequest;
import edu.member.student.dto.response.GetAccountPayResponse;
import edu.member.student.entity.AccountPay;
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
    public boolean checkAaccExist(AuthenticationRequest request){ // kiem tra xem tai khoan da kich hoat token hay chua
        boolean authen=identityClient.authenPass(request).getResult();
        if (!authen){
            return false;
        }

        Optional<AccountPay> acc= payRepository.findByEmail(request.getUsername());
        return acc.isPresent();
    }


}
