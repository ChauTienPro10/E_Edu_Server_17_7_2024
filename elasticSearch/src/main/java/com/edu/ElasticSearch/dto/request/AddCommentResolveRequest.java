package com.edu.ElasticSearch.dto.request;

import com.edu.ElasticSearch.entity.PracticeResolve;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddCommentResolveRequest {

    String content;
    String email;
    String resolveId;
}
