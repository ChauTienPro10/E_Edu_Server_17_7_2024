package com.edu.ElasticSearch.services;

import com.edu.ElasticSearch.dto.request.CreateDiscountRequest;
import com.edu.ElasticSearch.dto.response.ApiResponse;
import com.edu.ElasticSearch.dto.response.CreateDiscountResponse;
import com.edu.ElasticSearch.entity.Discount;
import com.edu.ElasticSearch.mapper.DiscountMapper;
import com.edu.ElasticSearch.repository.DiscountRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class DiscountService {
    @Autowired
    DiscountRepository discountRepository;
    @Autowired
    DiscountMapper discountMapper;
    public ApiResponse<CreateDiscountResponse> new_discount(CreateDiscountRequest request){
        try{
            if(discountRepository.findByCode(request.getCode()).isPresent()
            || discountRepository.findByName(request.getName()).isPresent()){
                return ApiResponse.<CreateDiscountResponse>builder()
                        .code(501)
                        .message("Hệ thống đã ghi nhận khuyến mãi này từ trước ")
                        .build();
            }
            Discount discount=discountRepository.save(
                    discountMapper.toDiscount(
                            request
                    )
            );
            discount.setStatus(true);
            return ApiResponse.<CreateDiscountResponse>builder()
                    .code(1000)
                    .message("Thêm thành công ")
                    .result(discountMapper.toResponseDiscount(discount))
                    .build();
        }
        catch (Exception e){
            return ApiResponse.<CreateDiscountResponse>builder()
                    .code(500)
                    .message("Lỗi mãy chủ xử lý không thanh công")
                    .build();
        }
    }

    public Discount find_By_code(String code){
        return discountRepository.findByCode(code).get();
    }
}
