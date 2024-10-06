package edu.member.student.service;

import edu.member.student.dto.request.DeleteCommentRequest;
import edu.member.student.entity.Comment;
import edu.member.student.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CommentService {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    JwtDecoder jwtDecoder;
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
    public List<Comment> getAllCmt(String courseid ){
        return commentRepository.findByCourse(courseid);
    }
    public boolean deleteCmt(DeleteCommentRequest requets,String authorizationHeader){
        String token = authorizationHeader.startsWith("Bearer ") ? authorizationHeader.substring(7) : authorizationHeader;
        Jwt jwt=jwtDecoder.decode(token);
        String role = jwtUtil.extractRole(jwt);
        Optional<Comment> cmt=commentRepository.findById((requets.getId()));
        if(role.equals("ROLE_USER")){

            if(cmt.isEmpty()){
                return false;
            }
            if(cmt.get().getEmail().equals(requets.getEmail())){
                commentRepository.delete(cmt.get());
                return true;
            }
            return false;
        }
        if(role.equals("ROLE_TEACHER")){
            if(cmt.isEmpty()){
                return false;
            }
            commentRepository.delete(cmt.get());
            return true;
        }
        return false;

    }

}
