package edu.member.student.dto.response;

import lombok.*;
import org.springframework.data.annotation.Id;

@Data
public class VoucherType {
    @Id
    private String id;
    private String type;
}
