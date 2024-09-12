package com.edu.ElasticSearch.repository;

import com.edu.ElasticSearch.entity.AccountPay;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountPayRepository extends ElasticsearchRepository<AccountPay,String> {
}
