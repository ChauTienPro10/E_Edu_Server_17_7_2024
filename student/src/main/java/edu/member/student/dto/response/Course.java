package edu.member.student.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Course {
    private String id;
    private String title;
    private String description;
    private double price;
    private int duration;
    private String teacher;
    private String subject;
    private int level;
}
