package com.edu.ElasticSearch.controller;

import com.edu.ElasticSearch.dto.request.CreateDiscountRequest;
import com.edu.ElasticSearch.dto.response.ApiResponse;
import com.edu.ElasticSearch.dto.response.CreateDiscountResponse;
import com.edu.ElasticSearch.entity.Discount;
import com.edu.ElasticSearch.services.DiscountService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/discount")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DiscountControler {
    @Autowired
    DiscountService discountService;
    @PostMapping("/new")
    public ApiResponse<CreateDiscountResponse> new_discount(@RequestBody CreateDiscountRequest request){
        return discountService.new_discount(request);
    }
    @GetMapping("/find_by_code")
    public Discount find_by_code(@RequestParam String code){
        try{
            return discountService.find_By_code(code);
        }
        catch (Exception e){
            return null;
        }
    }
}
