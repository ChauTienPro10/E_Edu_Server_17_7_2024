package edu.member.student.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentCreationRequest {
    String userId;
    String firstName;
    String lastName;
    LocalDate dob;
    String level;
    String phone;
    String email;
}
