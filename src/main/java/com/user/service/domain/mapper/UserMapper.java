package com.user.service.domain.mapper;

import com.user.service.domain.dto.request.UserRequest;
import com.user.service.domain.dto.response.UserResponse;
import com.user.service.domain.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse userToUserDTO(User user);

    User userRequestToUser(UserRequest userRequest);

}
