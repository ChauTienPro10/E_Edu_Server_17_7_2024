package com.edu.ElasticSearch.mapper;

import com.edu.ElasticSearch.dto.request.CreateDiscountRequest;
import com.edu.ElasticSearch.dto.response.CreateDiscountResponse;
import com.edu.ElasticSearch.entity.Discount;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DiscountMapper {
    Discount toDiscount (CreateDiscountRequest request);
    CreateDiscountResponse toResponseDiscount(Discount entity);
}
