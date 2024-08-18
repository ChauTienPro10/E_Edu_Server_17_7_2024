package edu.member.student.repository.httpClients;

import edu.member.student.dto.request.UserCreateRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="identity-service", url="http://127.0.0.1:8080/identity")
public interface IdentityClient {
    @GetMapping(value = "/auth/hl",produces = MediaType.APPLICATION_JSON_VALUE)
    String hello();
    @PostMapping(value = "/users/registration")
    Object createNewAccount(@RequestBody UserCreateRequest request);

}
