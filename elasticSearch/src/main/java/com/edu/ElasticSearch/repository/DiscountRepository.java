package com.edu.ElasticSearch.repository;

import com.edu.ElasticSearch.entity.Discount;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiscountRepository extends ElasticsearchRepository<Discount,String> {
    Optional<Discount> findByCode(String code);
    Optional<Discount> findByName(String name);
    Discount save(Discount entity);

}
