package edu.app.gateway.dto.response;


import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Video {
    private String id;

    private String title;

    private String description;

    private String course;

    private String link;

    private int stt;
}
