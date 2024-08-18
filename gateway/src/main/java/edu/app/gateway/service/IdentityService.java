package edu.app.gateway.service;

import edu.app.gateway.dto.request.IntrospectRequest;
import edu.app.gateway.dto.response.ApiResponse;
import edu.app.gateway.dto.response.IntrospectResponse;
import edu.app.gateway.repository.IdentityClient;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class IdentityService {
    IdentityClient identityClient;

    public Mono<ApiResponse<IntrospectResponse>> introspect(String token){
        return identityClient.introspect(IntrospectRequest.builder()
                .token(token)
                .build());
    }
}
