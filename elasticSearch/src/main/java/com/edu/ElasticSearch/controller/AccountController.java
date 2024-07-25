package com.edu.ElasticSearch.controller;

import com.edu.ElasticSearch.dto.response.AccountResponse;
import com.edu.ElasticSearch.dto.response.SearchByNameResponse;
import com.edu.ElasticSearch.entity.Account;
import com.edu.ElasticSearch.services.AccountService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountController {
    @Autowired
    private AccountService accountService;
    @GetMapping("/{id}")
    public Optional<AccountResponse> getProductById(@PathVariable Long id) {
        return accountService.findById(id);
    }
    @GetMapping("/search/{name}")
    public SearchByNameResponse searchByName(@PathVariable String name) {
        return accountService.findByName(name);
    }
}
