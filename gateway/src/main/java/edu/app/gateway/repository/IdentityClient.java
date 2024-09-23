package edu.app.gateway.repository;

import edu.app.gateway.dto.request.AuthenticationRequest;
import edu.app.gateway.dto.request.IntrospectRequest;
import edu.app.gateway.dto.request.LogoutRequest;
import edu.app.gateway.dto.response.ApiResponse;
import edu.app.gateway.dto.response.AuthenticationResponse;
import edu.app.gateway.dto.response.IntrospectResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Mono;

public interface IdentityClient {
    @PostExchange(url = "/auth/introspect", contentType = MediaType.APPLICATION_JSON_VALUE)
    Mono<ApiResponse<IntrospectResponse>> introspect(@RequestBody IntrospectRequest request);

    @PostExchange(url = "/auth/token",contentType = MediaType.APPLICATION_JSON_VALUE)
    Mono<ApiResponse<AuthenticationResponse>> login(@RequestBody AuthenticationRequest request);

    @PostExchange(url = "/auth/logout",contentType = MediaType.APPLICATION_JSON_VALUE)
    Mono<ApiResponse<Void>> logout(@RequestBody LogoutRequest request);
}

