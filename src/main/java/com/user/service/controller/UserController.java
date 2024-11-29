package com.user.service.controller;

import com.user.service.domain.dto.request.UserRequest;
import com.user.service.domain.dto.response.UserResponse;
import com.user.service.service.UserService;
import com.user.service.util.api.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<UserResponse> addUser(@Valid @RequestBody UserRequest userRequest) {
        UserResponse userResponse = userService.addUser(userRequest);
        return new ApiResponse<>(userResponse);
    }

}
