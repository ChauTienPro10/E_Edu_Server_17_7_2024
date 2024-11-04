package com.edu.ElasticSearch.mapper;

import com.edu.ElasticSearch.dto.request.CreateDiscountRequest;
import com.edu.ElasticSearch.dto.response.CreateDiscountResponse;
import com.edu.ElasticSearch.entity.Voucher;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VoucherMapper {
    Voucher toDiscount (CreateDiscountRequest request);
    CreateDiscountResponse toResponseDiscount(Voucher entity);
}
