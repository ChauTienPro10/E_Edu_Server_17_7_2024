package com.edu.ElasticSearch.services;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    /**
     * Trích xuất username từ JWT.
     * @param jwt JWT đã được giải mã.
     * @return Username từ JWT.
     */
    public String extractUsername(Jwt jwt) {
        // Trích xuất username từ claims
        return (String) jwt.getClaims().get("sub"); // Sử dụng key phù hợp với cấu hình JWT của bạn
    }
}

