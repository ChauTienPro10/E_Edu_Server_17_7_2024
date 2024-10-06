package edu.member.student.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

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
    public String extractRole(Jwt jwt) {
        // Trích xuất role từ claim "scope"
        return jwt.getClaimAsString("scope");
    }
}

