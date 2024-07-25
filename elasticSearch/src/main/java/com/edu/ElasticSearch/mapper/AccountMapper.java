package com.edu.ElasticSearch.mapper;

import com.edu.ElasticSearch.dto.response.AccountResponse;
import com.edu.ElasticSearch.entity.Account;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountResponse toAccountResponse(Account entity);
    List<AccountResponse> toAccountResponseList(List<Account> accounts);
}
