package edu.member.student.repository.httpClients;

import edu.member.student.dto.request.AuthenticationRequest;
import edu.member.student.dto.request.UserCreateRequest;
import edu.member.student.dto.response.ApiResponse;
import edu.member.student.dto.response.RegisterResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

@FeignClient(name="identity-service", url="http://${IDENTITY_URL:127.0.0.1}:8080/identity")
public interface IdentityClient {

    @PostMapping(value = "/users/registration")
    RegisterResponse createNewAccount(@RequestBody UserCreateRequest request);
    @PostMapping(value = "/auth/wallet.authen.password",produces = MediaType.APPLICATION_JSON_VALUE)
    ApiResponse<Boolean> authenPass(@RequestBody AuthenticationRequest request);
}
