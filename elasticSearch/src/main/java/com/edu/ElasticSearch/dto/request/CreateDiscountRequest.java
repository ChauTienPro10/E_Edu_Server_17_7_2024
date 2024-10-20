package com.edu.ElasticSearch.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateDiscountRequest {

    String name;
    String type; // loai khuyến mãi
    int discount_value;
    String start; // ngày bắt đầu
    String end; // ngày kết thúc
    List<String> applyfor; // ap dung cho khoa hoc nao
    String valid_user; // các đối tượng nào có thể sử dụng
    String code; // mã của khuyến mãi

}
