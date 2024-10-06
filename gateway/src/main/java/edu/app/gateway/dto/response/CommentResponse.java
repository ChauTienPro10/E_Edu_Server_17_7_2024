package edu.app.gateway.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;


import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentResponse {

    String id;
    String email;
    String course; // luu id khoa hoc
    String content;
    LocalDateTime time;
}
