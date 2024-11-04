package com.edu.ElasticSearch.services;


import com.edu.ElasticSearch.dto.request.NewVoucherRequest;
import com.edu.ElasticSearch.entity.Discount;
import com.edu.ElasticSearch.entity.Voucher;
import com.edu.ElasticSearch.entity.VoucherType;
import com.edu.ElasticSearch.repository.VTypeRepository;
import com.edu.ElasticSearch.repository.VoucherRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class VoucherService {
    @Autowired
    VoucherRepository voucherRepository;
    @Autowired
    VTypeRepository vTypeRepository;
    public Voucher addVoucherDiscount(NewVoucherRequest request){
        Optional<VoucherType> type=vTypeRepository.findByType("DISCOUNT");
        if(type.isEmpty()){
            return null;
        }
        Voucher newVoucher=new Discount();
        newVoucher.setName(request.getName());
        newVoucher.setVoucherType(type.get());
        newVoucher.setAmount(request.getAmount());
        return voucherRepository.save(newVoucher);
    }
}
