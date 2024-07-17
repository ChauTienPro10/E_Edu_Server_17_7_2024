package com.edu.app.mapper;


import com.edu.app.dto.request.UserCreateRequest;
import com.edu.app.dto.request.UserUpdateRequest;
import com.edu.app.dto.response.UserResponse;
import com.edu.app.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreateRequest request);

    UserResponse toUserResponse(User user);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
