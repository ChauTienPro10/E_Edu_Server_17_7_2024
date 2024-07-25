package com.edu.ElasticSearch.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountResponse {
    Long account_number;
    Long balance;
    String firstname;
    String lastname;
    int age;
    String gender;
    String address;
    String employer;
    String email;
    String city;
    String state;
}
