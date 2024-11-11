package edu.app.gateway.object;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentOfResolve {
    String id;
    String content;
    String email;
    LocalDateTime timestamp;
    PracticeResolve practiceResolve;
}
