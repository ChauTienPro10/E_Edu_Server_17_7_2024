package com.edu.ElasticSearch.services;

import com.edu.ElasticSearch.dto.response.AccountResponse;
import com.edu.ElasticSearch.dto.response.SearchByNameResponse;

import com.edu.ElasticSearch.mapper.AccountMapper;
import com.edu.ElasticSearch.repository.AccountRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    AccountMapper accountMapper;
    public Optional<AccountResponse> findById(Long id) {
        return accountRepository.findById(id)
                .map(accountMapper::toAccountResponse);
    }
    public SearchByNameResponse findByName(String searchTerm) {
        return null;
    }
}
