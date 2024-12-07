package com.user.service.service;

import com.user.service.domain.dto.request.UserRequest;
import com.user.service.domain.dto.response.UserResponse;
import com.user.service.domain.entity.User;
import com.user.service.domain.mapper.UserMapper;
import com.user.service.exception.UserNotFoundException;
import com.user.service.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public Page<UserResponse> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper::userToUserDTO);
    }

    public UserResponse getUserByFirstName(String firstName) {
        User user = userRepository.findUserByFirstName(firstName).orElseThrow(UserNotFoundException::new);
        return userMapper.userToUserDTO(user);
    }

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

    @Transactional
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Transactional
    public UserResponse updateUser(Long userId, UserRequest userRequest) {
        User existingUser = getUser(userId);
        userMapper.updateUserFromRequest(userRequest, existingUser);
        User updatedUser = userRepository.save(existingUser);
        return userMapper.userToUserDTO(updatedUser);
    }

    User getUser(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            LOG.error("User with ID {} not found", userId);
            throw new UserNotFoundException();
        }
        return optionalUser.get();
    }

}
