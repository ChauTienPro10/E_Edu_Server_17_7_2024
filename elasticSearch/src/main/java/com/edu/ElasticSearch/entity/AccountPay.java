package com.edu.ElasticSearch.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName="account_pay")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountPay {
    @Id
    private String id;
    @Field(type = FieldType.Text, analyzer = "standard")
    private String idUser;
    @Field(type = FieldType.Text, analyzer = "standard")
    private String address;
    @Field(type = FieldType.Text, analyzer = "standard")
    private String key;

}
