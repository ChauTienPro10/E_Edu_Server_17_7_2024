package com.edu.ElasticSearch.repository.httpClients;


import com.edu.ElasticSearch.dto.request.AuthenticationRequest;
import com.edu.ElasticSearch.dto.request.UserCreateRequest;
import com.edu.ElasticSearch.dto.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="identity-service", url="http://127.0.0.1:8080/identity")
public interface IdentityClient {

    @PostMapping(value = "/users/teacher.registration")
    Object createNewAccount(@RequestBody UserCreateRequest request);
    @PostMapping(value = "/auth/wallet.authen.password",produces = MediaType.APPLICATION_JSON_VALUE)
    ApiResponse<Boolean> authenPass(@RequestBody AuthenticationRequest request);
}
