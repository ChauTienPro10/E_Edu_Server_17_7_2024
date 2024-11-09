package edu.app.gateway.object;

import lombok.*;
import lombok.experimental.FieldDefaults;

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
    Date timestamp;
    String content;
    PracticeResolve practiceResolve;
}
