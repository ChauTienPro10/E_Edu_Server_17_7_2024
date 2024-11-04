package com.edu.ElasticSearch.repository;


import com.edu.ElasticSearch.entity.Voucher;
import com.edu.ElasticSearch.entity.VoucherType;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoucherRepository extends ElasticsearchRepository<Voucher,String> {
    Voucher save(Voucher voucher);
    Voucher findById (String id);
    List<Voucher> findAll();
}
