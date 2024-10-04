package edu.member.student.controller;

import edu.member.student.dto.request.SoketRequest;
import edu.member.student.dto.response.SocketResponseDeposit;
import edu.member.student.repository.PayRepository;
import edu.member.student.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {
    @Autowired
    PayRepository payRepository;

    PayService payService;
    @MessageMapping("/sendMessage")  // Endpoint cho tin nhắn
    @SendTo("/topic/messages")  // Gửi tin nhắn tới các subscriber
    public SocketResponseDeposit sendMessage(SoketRequest request) throws Exception {
       return null;

    }
}
