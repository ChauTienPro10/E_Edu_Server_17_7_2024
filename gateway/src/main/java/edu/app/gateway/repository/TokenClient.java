package edu.app.gateway.repository;

import edu.app.gateway.dto.response.CourseResponse;
import edu.app.gateway.dto.response.tokenserver.BalanceResponse;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import reactor.core.publisher.Mono;

import java.util.List;

public interface TokenClient {
    @GetExchange(url = "/getAdTokens")
    BalanceResponse getCoursesByLevel();
}
