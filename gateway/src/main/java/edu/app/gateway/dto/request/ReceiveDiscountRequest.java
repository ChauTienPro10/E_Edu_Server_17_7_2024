package edu.app.gateway.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReceiveDiscountRequest {
    String email;
    String code; // ma giam gia
}
