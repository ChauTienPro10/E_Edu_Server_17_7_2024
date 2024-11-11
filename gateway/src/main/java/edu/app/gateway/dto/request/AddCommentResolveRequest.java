package edu.app.gateway.dto.request;

import edu.app.gateway.object.PracticeResolve;
import lombok.*;
import lombok.experimental.FieldDefaults;

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
