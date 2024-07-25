package com.edu.ElasticSearch.repository;

import com.edu.ElasticSearch.dto.response.AccountResponse;
import com.edu.ElasticSearch.entity.Account;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends ElasticsearchRepository<Account,Long> {
    List<Account> findByLastnameContaining(String name);
}
