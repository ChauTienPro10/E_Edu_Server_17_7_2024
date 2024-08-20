package com.edu.app.controllers;

import com.edu.app.dto.request.UserCreateRequest;
import com.edu.app.dto.response.ApiResponse;
import com.edu.app.dto.response.UserResponse;
import com.edu.app.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserController {
    UserService userService;
    @PostMapping("/registration")
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreateRequest request) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.crateNew(request))
                .build();
    }
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/testAutho")
    String getHl(){
        return "chau duong phat tien";
    }

}
