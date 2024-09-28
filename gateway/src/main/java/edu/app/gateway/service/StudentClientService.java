package edu.app.gateway.service;

import edu.app.gateway.dto.request.StudentCreationRequest;
import edu.app.gateway.dto.response.ApiResponse;
import edu.app.gateway.dto.response.StudentResponse;
import edu.app.gateway.repository.StudentClient;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;
import reactor.core.publisher.Mono;

import java.util.regex.Pattern;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class StudentClientService {
    @Autowired
    StudentClient studentClient;
    public Mono<String> getTest(@RequestHeader("Authorization") String token){
//        return identityClient.introspect(IntrospectRequest.builder().token(token).build());
        return studentClient.getTest(token);
    }




}
