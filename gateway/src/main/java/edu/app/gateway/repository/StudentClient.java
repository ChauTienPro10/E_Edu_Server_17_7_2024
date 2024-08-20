package edu.app.gateway.repository;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Mono;

public interface StudentClient {
    @GetExchange (url = "/profile/testAutho")
    Mono<String> getTest(@RequestHeader("Authorization") String token);
}
