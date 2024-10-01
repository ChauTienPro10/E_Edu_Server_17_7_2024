package edu.member.student.congfifuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");  // Sử dụng bộ xử lý tin nhắn đơn giản để xử lý các tin nhắn đến /topic
        config.setApplicationDestinationPrefixes("/app");  // Các tin nhắn có tiền tố "/app" sẽ được chuyển đến các controller
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").setAllowedOrigins("http://localhost:3000").withSockJS();  // Đăng ký điểm cuối WebSocket và kích hoạt SockJS
    }
}

