package edu.app.gateway.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class CreateNewPracticeRequest {
    private String content;
    private String teacherId;
    String courseId;
    Integer hardLevel;
}
