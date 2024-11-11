package edu.app.gateway.object;

import lombok.*;
import lombok.experimental.FieldDefaults;



@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Like {

    String id;
    String practiceResolveId;
    String email;

}
