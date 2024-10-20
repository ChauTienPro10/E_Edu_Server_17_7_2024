package com.edu.ElasticSearch.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Document(indexName="discount")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Discount {
    @Id
    private String id;
    String name;
    String type; // loai khuyến mãi
    int discount_value; // gia tri khau tru cho khuyen mai
    String start; // ngày bắt đầu
    String end; // ngày kết thúc
    List<String> applyfor; // ap dung cho khoa hoc nao
    String valid_user; // các đối tượng nào có thể sử dụng
    String code; // mã của khuyến mãi
    boolean status; // kiem tra trang thai ma co kha dung hay khong


}
