package edu.app.gateway.object;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ObserveOfResolve {
    String id;
    String resolveId;
    String studentId;
    LocalDateTime timestamp;
    String content;
    PracticeResolve practiceResolve;
}
