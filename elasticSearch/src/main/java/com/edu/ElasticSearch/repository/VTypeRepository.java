package com.edu.ElasticSearch.repository;

import com.edu.ElasticSearch.entity.Voucher;
import com.edu.ElasticSearch.entity.VoucherType;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;

public interface VTypeRepository extends ElasticsearchRepository<VoucherType,String> {
    VoucherType save(VoucherType voucherType);
    Optional<VoucherType> findByType(String type);
}
