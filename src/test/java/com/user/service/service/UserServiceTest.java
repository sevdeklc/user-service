package com.user.service.service;

import com.user.service.domain.dto.request.UserRequest;
import com.user.service.domain.dto.response.UserResponse;
import com.user.service.domain.entity.User;
import com.user.service.domain.mapper.UserMapper;
import com.user.service.exception.UserNotFoundException;
import com.user.service.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    private static final int MAX_PAGESIZE = 10;

    @Test
    void testGetAllUsers() {
        Pageable pageable = PageRequest.of(0, MAX_PAGESIZE);
        User user = new User();
        user.setId(1L);
        user.setFirstName("Test User");

        UserResponse userResponse = new UserResponse();
        userResponse.setId(1L);
        userResponse.setFirstName("Test User");

        Page<User> userPage = new PageImpl<>(List.of(user));

        when(userRepository.findAll(pageable)).thenReturn(userPage);
        when(userMapper.userToUserDTO(user)).thenReturn(userResponse);

        Page<UserResponse> result = userService.getAllUsers(pageable);

        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        assertEquals("Test User", result.getContent().get(0).getFirstName());

        verify(userRepository).findAll(pageable);
        verify(userMapper).userToUserDTO(user);
    }

    @Test
    void testAddUser() {
        UserRequest userRequest = new UserRequest();
        userRequest.setFirstName("New User");

        User user = new User();
        user.setFirstName("New User");

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setFirstName("New User");

        UserResponse userResponse = new UserResponse();
        userResponse.setId(1L);
        userResponse.setFirstName("New User");

        when(userMapper.userRequestToUser(userRequest)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(savedUser);
        when(userMapper.userToUserDTO(savedUser)).thenReturn(userResponse);

        UserResponse result = userService.addUser(userRequest);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("New User", result.getFirstName());

        verify(userMapper).userRequestToUser(userRequest);
        verify(userRepository).save(user);
        verify(userMapper).userToUserDTO(savedUser);
    }

    @Test
    void testDeleteUser() {
        Long userId = 1L;

        doNothing().when(userRepository).deleteById(userId);

        userService.deleteUser(userId);

        verify(userRepository).deleteById(userId);
    }

    @Test
    void testUpdateUser() {
        Long userId = 1L;
        UserRequest userRequest = new UserRequest();
        userRequest.setFirstName("Updated User");

        User existingUser = new User();
        existingUser.setId(1L);
        existingUser.setFirstName("Old User");

        User updatedUser = new User();
        updatedUser.setId(1L);
        updatedUser.setFirstName("Updated User");

        UserResponse userResponse = new UserResponse();
        userResponse.setId(1L);
        userResponse.setFirstName("Updated User");

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        doNothing().when(userMapper).updateUserFromRequest(userRequest, existingUser);
        when(userRepository.save(existingUser)).thenReturn(updatedUser);
        when(userMapper.userToUserDTO(updatedUser)).thenReturn(userResponse);

        UserResponse result = userService.updateUser(userId, userRequest);

        assertNotNull(result);
        assertEquals("Updated User", result.getFirstName());

        verify(userRepository).findById(userId);
        verify(userMapper).updateUserFromRequest(userRequest, existingUser);
        verify(userRepository).save(existingUser);
        verify(userMapper).userToUserDTO(updatedUser);
    }

    @Test
    void testGetUserSuccess() {
        Long userId = 1L;
        User user = new User();
        user.setId(1L);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        User result = userService.getUser(userId);

        assertNotNull(result);
        assertEquals(userId, result.getId());

        verify(userRepository).findById(userId);
    }

    @Test
    void testGetUserNotFound() {
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getUser(userId));

        verify(userRepository).findById(userId);
    }

}
