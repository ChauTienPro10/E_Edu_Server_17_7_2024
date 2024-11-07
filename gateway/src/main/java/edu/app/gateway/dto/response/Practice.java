package edu.app.gateway.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Practice {
    private String id;
    private String content;
    private Integer hardLevel;
    String courseId;
}
