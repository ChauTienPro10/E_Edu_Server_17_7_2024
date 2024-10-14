package edu.member.student.controller;

import edu.member.student.dto.response.ApiResponse;
import edu.member.student.dto.response.Management;
import edu.member.student.service.ManagementService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/manager")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ManagementControler {
    @Autowired
    ManagementService managementService;
    @GetMapping("/getInforManagement")
    public ApiResponse<Management> getinforManagement(){
        log.info("getting infor");
        return managementService.getInforManagement();
    }
}
