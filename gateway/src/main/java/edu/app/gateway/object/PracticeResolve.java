package edu.app.gateway.object;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PracticeResolve {

    String id;
    String studentEmail;
    String practiceId;
    String result;
    LocalDateTime timestamp;
    Integer numOfLike;
    List<ObserveOfResolve> observes;

}
