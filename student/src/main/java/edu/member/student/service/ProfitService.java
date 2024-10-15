package edu.member.student.service;

import edu.member.student.entity.Profit;
import edu.member.student.repository.ProfitRepossitory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ProfitService {
    @Autowired
    ProfitRepossitory profitRepossitory;
    public Profit Statist(){
        return null;
    }

}
