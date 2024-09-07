package com.edu.ElasticSearch.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Document(indexName="infor_course")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InforCourse {
    @Id
    private String id;
    @Field(type = FieldType.Text, analyzer = "standard")
    private List<String> methods;
    @Field(type = FieldType.Text, analyzer = "standard")
    private List<String> requires;
    @Field(type = FieldType.Text, analyzer = "standard")
    private String course;
    @Field(type = FieldType.Text, analyzer = "standard")
    private String descripContent;
}
