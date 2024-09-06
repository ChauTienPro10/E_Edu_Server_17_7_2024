package com.edu.ElasticSearch.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseResponse {
    private String id;
    private String title;
    private String description;
    private double price;
    private int duration;
    private String teacher;
    private int level;
}
