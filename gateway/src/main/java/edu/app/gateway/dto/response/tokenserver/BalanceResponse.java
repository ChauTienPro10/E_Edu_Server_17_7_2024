package edu.app.gateway.dto.response.tokenserver;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BalanceResponse {
    Double balance;
}
