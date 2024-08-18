package edu.member.student.mapper;

import edu.member.student.dto.request.StudentCreationRequest;
import edu.member.student.dto.request.UserCreateRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserIdentityMapper {
    UserCreateRequest toUserCreateRequest(StudentCreationRequest httprequest);
}
