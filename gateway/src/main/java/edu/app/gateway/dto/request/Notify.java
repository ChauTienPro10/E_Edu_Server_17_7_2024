package edu.app.gateway.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;


import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Notify {

    String id;
    String content;
    String link;
    LocalDateTime time;

}
