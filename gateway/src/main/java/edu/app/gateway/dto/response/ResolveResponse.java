package edu.app.gateway.dto.response;

import edu.app.gateway.object.ObserveOfResolve;
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
    String result;
    LocalDateTime timestamp;
    Integer numOfLike;

    List<ObserveOfResolve> observes;

}
