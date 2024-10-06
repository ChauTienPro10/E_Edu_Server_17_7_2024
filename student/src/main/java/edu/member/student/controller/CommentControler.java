package edu.member.student.controller;

import edu.member.student.dto.request.DeleteCommentRequest;
import edu.member.student.entity.Comment;
import edu.member.student.service.CommentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentControler {
    @Autowired
    CommentService commentService;
    @GetMapping("/get.comment")
    public List<Comment> getComment(@RequestParam String idcourse){
        return commentService.getAllCmt(idcourse);
    }
    @PostMapping("/delete.comment")
    public boolean deleteCmt(@RequestBody DeleteCommentRequest request
            ,@RequestHeader("Authorization") String authorizationHeader){
        return commentService.deleteCmt(request,authorizationHeader);
    }
}
