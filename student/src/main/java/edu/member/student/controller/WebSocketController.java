package edu.member.student.controller;

import edu.member.student.service.PayService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    PayService payService;
    @MessageMapping("/sendMessage")  // Endpoint cho tin nhắn
    @SendTo("/topic/messages")  // Gửi tin nhắn tới các subscriber
    public String sendMessage(String message) throws Exception {
        // Xử lý tin nhắn (có thể thêm logic tùy ý)
        System.out.println(message);
        if(message.equals("deposited")){
            return "200";
        }
        return "Hello, " + message + "!";  // Gửi tin nhắn đến tất cả subscriber
    }
}
