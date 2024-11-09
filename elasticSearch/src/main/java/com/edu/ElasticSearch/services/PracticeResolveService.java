package com.edu.ElasticSearch.services;

import com.edu.ElasticSearch.dto.request.CreateResolveRequest;
import com.edu.ElasticSearch.dto.response.ApiResponse;
import com.edu.ElasticSearch.dto.response.ResolveResponse;
import com.edu.ElasticSearch.entity.ObserveOfResolve;
import com.edu.ElasticSearch.entity.Practice;
import com.edu.ElasticSearch.entity.PracticeResolve;
import com.edu.ElasticSearch.exception.ErrorCode;
import com.edu.ElasticSearch.repository.PracticeRepository;
import com.edu.ElasticSearch.repository.PracticeResolveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

@Service
public class PracticeResolveService {
    @Autowired PracticeResolveRepository practiceResolveRepository;
    @Autowired
    PracticeRepository practiceRepository;

    public ApiResponse<ResolveResponse> saveNewPracticeResolve(CreateResolveRequest request){

        if(practiceRepository.findById(request.getPracticeId()).isEmpty()){
            return ApiResponse.<ResolveResponse>builder()
                    .code(ErrorCode.ERR_PRACTICE_ID_NOT_EXIST.getCode())
                    .message(ErrorCode.ERR_PRACTICE_ID_NOT_EXIST.getMessage())
                    .build();
        }
        try{


            PracticeResolve newResolve=PracticeResolve.builder()
                    .numOfLike(0)
                    .practiceId(request.getPracticeId())
                    .observes(new ArrayList<ObserveOfResolve>())
                    .timestamp(LocalDateTime.now())
                    .studentEmail(request.getStudentEmail())
                    .result(request.getResult())
                    .build();
            practiceResolveRepository.save(newResolve);
            return ApiResponse.<ResolveResponse>builder()
                    .code(1000)
                    .message("OK")
                    .result(ResolveResponse.builder()
                            .id(newResolve.getId())
                            .timestamp(newResolve.getTimestamp())
                            .practiceId(newResolve.getPracticeId())
                            .numOfLike(newResolve.getNumOfLike())
                            .studentEmail(newResolve.getStudentEmail())
                            .observes(newResolve.getObserves())
                            .result(request.getResult())
                            .build())
                    .build();

        }
        catch (Exception e){
            return ApiResponse.<ResolveResponse>builder()
                    .result(null)
                    .code(500)
                    .message("Error from server")
                    .build();
        }
    }

}
