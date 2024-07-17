package edu.app.gateway.service;

import edu.app.gateway.repository.StudentClient;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class StudentClientService {
    @Autowired
    StudentClient studentClient;
    public Mono<String> getTest(){
//        return identityClient.introspect(IntrospectRequest.builder().token(token).build());
        return studentClient.getTest();
    }
}
