package com.edu.ElasticSearch.services;

import com.edu.ElasticSearch.dto.response.AccountResponse;
import com.edu.ElasticSearch.dto.response.SearchByNameResponse;

import com.edu.ElasticSearch.entity.Account;
import com.edu.ElasticSearch.mapper.AccountMapper;
import com.edu.ElasticSearch.repository.AccountRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    AccountMapper accountMapper;
    @Autowired  private ElasticsearchOperations elasticsearchOperations;
    public Optional<AccountResponse> findById(Long id) {
        return accountRepository.findById(id)
                .map(accountMapper::toAccountResponse);
    }
    public SearchByNameResponse findByName(String searchTerm) {
//         Tạo Criteria để tìm kiếm theo trường "lastName"
        Criteria criteria = Criteria.where("lastname").matches(searchTerm);
        // Tạo CriteriaQuery dựa trên Criteria đã tạo
        CriteriaQuery query = new CriteriaQuery(criteria);
        // Thực hiện tìm kiếm bằng ElasticsearchOperations
        SearchHits<Account> searchHits = elasticsearchOperations.search(query, Account.class);
        // Chuyển đổi kết quả tìm kiếm từ SearchHits<Account> thành List<AccountResponse>
        List<AccountResponse> accountResponses=accountMapper.toAccountResponseList(
                searchHits.get().map(hit -> hit.getContent()).collect(Collectors.toList())
        );
        return new SearchByNameResponse(accountResponses);
    }
}
