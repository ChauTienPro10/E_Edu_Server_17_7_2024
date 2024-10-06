package edu.member.student.service;

import edu.member.student.entity.Comment;
import edu.member.student.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;
    public Comment saveComment(String message,String email,String course){
        try{
            return commentRepository.save(Comment.builder()
                    .content(message)
                    .time(LocalDateTime.now())
                    .email(email)
                    .course(course)
                    .build());

        }
        catch (Exception e){
            log.info(e.toString());
            return null;
        }

    }
    public List<Comment> getAllCmt(String courseid){
        return commentRepository.findByCourse(courseid);
    }

}
