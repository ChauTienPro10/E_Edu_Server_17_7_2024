package edu.member.student.dto.response;

import lombok.*;
import org.springframework.data.annotation.Id;

@Data
public class Voucher {
    @Id
    private String code;
    private String name ;  // Default value set to false
    private Double amount;
    private VoucherType voucherType;
}
