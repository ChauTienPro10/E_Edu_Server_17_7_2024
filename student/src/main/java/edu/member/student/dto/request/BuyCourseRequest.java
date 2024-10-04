package edu.member.student.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BuyCourseRequest {
    String id;
    String email;
    String course; // id course
    LocalDateTime time;
    int price;
}
