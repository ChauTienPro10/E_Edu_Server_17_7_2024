package edu.app.gateway.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InforCourse {
    private String id;
    private List<String> methods;
    private List<String> requires;
    private String course;
    private String descripContent;
}
