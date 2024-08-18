package com.edu.app.service;

import com.edu.app.constant.PredefinedRole;
import com.edu.app.dto.request.UserCreateRequest;
import com.edu.app.dto.response.UserResponse;
import com.edu.app.entity.Role;
import com.edu.app.entity.User;
import com.edu.app.exception.AppException;
import com.edu.app.exception.ErrorCode;
import com.edu.app.mapper.UserMapper;
import com.edu.app.repository.RoleRepository;
import com.edu.app.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;



    public UserResponse crateNew(UserCreateRequest request){
        if(userRepository.existsByUsername(request.getUsername())) throw new AppException(ErrorCode.USER_EXISTED);
        User user=userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        HashSet<Role> roles = new HashSet<>();
        roleRepository.findById(PredefinedRole.USER_ROLE).ifPresent(roles::add);
        user.setRoles(roles);
        user = userRepository.save(user);
//        var profileRequest = profileMapper.toProfileCreationRequest(request);
//        profileRequest.setUserId(user.getId());
        return userMapper.toUserResponse(user);

    }
}
