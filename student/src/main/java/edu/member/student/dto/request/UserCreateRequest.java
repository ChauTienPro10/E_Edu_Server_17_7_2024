package edu.member.student.dto.request;


import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateRequest {
    String memberId;
    @Size(min = 4, message = "USERNAME_INVALID")
    String username;

    @Size(min = 6, message = "INVALID_PASSWORD")
    String password;
}
