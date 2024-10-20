package edu.app.gateway.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class MyDiscount {

    String id;

    String discountcode;
    String student;
    boolean status;
}
