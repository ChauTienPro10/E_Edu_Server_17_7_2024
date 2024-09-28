package edu.app.gateway.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateTeacherResponse {
    private String id;
    private String name;
    private String major;
    private int level;
}
