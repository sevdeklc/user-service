package com.user.service.domain.mapper;

import com.user.service.domain.dto.request.UserRequest;
import com.user.service.domain.dto.response.UserResponse;
import com.user.service.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse userToUserDTO(User user);

    User userRequestToUser(UserRequest userRequest);

    @Mapping(target = "id", ignore = true)
    void updateUserFromRequest(UserRequest userRequest, @MappingTarget User user);

}
