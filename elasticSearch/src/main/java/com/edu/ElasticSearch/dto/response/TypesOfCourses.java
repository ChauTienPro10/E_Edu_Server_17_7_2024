package com.edu.ElasticSearch.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TypesOfCourses {
    long thpt;
    long thcs;
    long th;
}
