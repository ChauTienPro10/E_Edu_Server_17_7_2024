package edu.app.gateway.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateDiscountResponse {
    private String id;
    String name;
    String type; // loai khuyến mãi
    int discount_value;
    String start; // ngày bắt đầu
    String end; // ngày kết thúc
    List<String> applyfor; // ap dung cho khoa hoc nao
    String valid_user; // các đối tượng nào có thể sử dụng
    String code; // mã của khuyến mãi
    boolean status; // kiem tra trang thai ma co kha dung hay khong
}
