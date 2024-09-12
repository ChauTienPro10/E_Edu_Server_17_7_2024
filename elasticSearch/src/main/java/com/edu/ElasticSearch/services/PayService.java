package com.edu.ElasticSearch.services;

import com.edu.ElasticSearch.dto.request.GetAccountPayRequest;
import com.edu.ElasticSearch.dto.response.GetAccountPayResponse;
import com.edu.ElasticSearch.entity.AccountPay;
import com.edu.ElasticSearch.repository.AccountPayRepository;
import com.edu.ElasticSearch.repository.httpClients.TokenServerClient;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PayService {
    @Autowired
    TokenServerClient tokenServerClient;
    AccountPayRepository accountPayRepository; // quan ly repo tai khoan thanh toan
    public AccountPay newAccountPay(GetAccountPayRequest verCode){
        GetAccountPayResponse response= tokenServerClient.getAccount(verCode);
        if(response!=null){
            AccountPay newAcc=AccountPay.builder()
                    .key(response.getPrivateKey())
                    .address(response.getAddress())
                    .idUser(verCode.getMemberId())
                    .build();
            return accountPayRepository.save(newAcc);
        }
        return null;
    }

    public String helo(){
        return tokenServerClient.hello();
    }

    public String createCode(){
        return UUID.randomUUID().toString();
    }
}
