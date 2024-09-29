package edu.app.gateway.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateCourseRequest {
    private String title;
    private String description;
    private double price;
    private int duration;
    String teacher;
    String subject;
    private int level;
}
