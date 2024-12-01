package com.user.service.service;

import com.user.service.domain.dto.request.UserRequest;
import com.user.service.domain.dto.response.UserResponse;
import com.user.service.domain.entity.User;
import com.user.service.domain.mapper.UserMapper;
import com.user.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserResponse addUser(UserRequest userRequest) {
        try {
            User user = userMapper.userRequestToUser(userRequest);
            User savedUser = userRepository.save(user);

            LOG.info("Successfully added user with ID: {}", savedUser.getId());

            return userMapper.userToUserDTO(savedUser);
        } catch (Exception e) {
            LOG.error("Error while adding user", e);
            throw e;
        }
    }

}
