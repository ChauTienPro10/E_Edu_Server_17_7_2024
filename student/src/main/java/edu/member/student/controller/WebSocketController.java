package edu.member.student.controller;

import edu.member.student.dto.request.CommentSocketRequest;
import edu.member.student.entity.Comment;
import edu.member.student.repository.PayRepository;
import edu.member.student.service.CommentService;
import edu.member.student.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {
    @Autowired
    PayRepository payRepository;
    @Autowired CommentService commentService;
    PayService payService;
    @MessageMapping("/sendMessage")  // Endpoint cho tin nhắn
    @SendTo("/topic/messages")  // Gửi tin nhắn tới các subscriber
    public Comment handleMessage(CommentSocketRequest messageDTO) {
        // You can now access the deserialized JSON object as a MessageDTO object
        System.out.println("Received message: " + messageDTO.getUsername());
        return commentService.saveComment(messageDTO.getContent(), messageDTO.getUsername(),messageDTO.getCourseid());
    }
}
