package com.edu.app.mapper;

import com.edu.app.dto.request.ProfileCreationRequest;
import com.edu.app.dto.request.UserCreateRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface profileMapper {
    ProfileCreationRequest toProfileCreationRequest(UserCreateRequest request);

}
