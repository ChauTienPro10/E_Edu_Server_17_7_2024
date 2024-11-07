package com.edu.ElasticSearch.controller;


import com.edu.ElasticSearch.dto.request.NewVoucherRequest;
import com.edu.ElasticSearch.entity.Voucher;
import com.edu.ElasticSearch.entity.VoucherType;
import com.edu.ElasticSearch.repository.VTypeRepository;
import com.edu.ElasticSearch.repository.VoucherRepository;
import com.edu.ElasticSearch.services.VoucherService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/voucher")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class VoucherControler {
    @Autowired
    VoucherService voucherService;
    @Autowired
    VTypeRepository vTypeRepository;


    @PostMapping("/new/voucher_type")
    public VoucherType newVoucherType(@RequestBody VoucherType voucherType){
        return vTypeRepository.save(voucherType);
    }

    @PostMapping("/new")
    public Voucher addVoucherDiscount(@RequestBody  NewVoucherRequest request){

        return voucherService.addVoucherDiscount(request);
    }

    @Autowired
    VoucherRepository voucherRepository;
    @GetMapping("/findByCode")
    public Voucher findByCode(@RequestParam String code){
        return voucherRepository.findById(code);
    }


    @GetMapping("/get_all_voucher")
    public List<Voucher> getAllVoucher(){
        return voucherService.getAllVoucher();
    }
}
