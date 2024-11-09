package com.edu.ElasticSearch.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

@Document(indexName="observe_of_resolve")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ObserveOfResolve {
    @Id
    String id;
    String resolveId;
    String studentId;
    Date timestamp;
    String content;
    PracticeResolve practiceResolve;
}
