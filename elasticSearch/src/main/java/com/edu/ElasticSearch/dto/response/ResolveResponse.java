package com.edu.ElasticSearch.dto.response;

import com.edu.ElasticSearch.entity.CommentOfResolve;
import com.edu.ElasticSearch.entity.Like;
import com.edu.ElasticSearch.entity.ObserveOfResolve;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResolveResponse {
    String id;
    String studentEmail;
    String practiceId;
    String content;
    LocalDateTime timestamp;
    List<Like> likes;


    List<ObserveOfResolve> observes;
    List<CommentOfResolve> comment;


}
