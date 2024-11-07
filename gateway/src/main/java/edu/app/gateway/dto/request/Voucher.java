package edu.app.gateway.dto.request;

import lombok.Data;

@Data
public class Voucher {
    private String code;
    private String name ;  // Default value set to false
    private Double amount;
    private VoucherType voucherType;
    private String linkTo ;
}
