package com.user.service.controller;

import com.user.service.domain.dto.request.UserRequest;
import com.user.service.domain.dto.response.UserResponse;
import com.user.service.service.UserService;
import com.user.service.util.api.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<Page<UserResponse>> getAllUsers(@RequestParam(value = "page_size", defaultValue = "20") Integer pageSize,
                                                       @RequestParam(value = "page_number", defaultValue = "0") Integer pageNumber) {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<UserResponse> users = userService.getAllUsers(pageable);
        return new ApiResponse<>(users);
    }

    @GetMapping("/firstName/{firstName}")
    public ApiResponse<UserResponse> getUserByFirstName(@PathVariable String firstName) {
        UserResponse userResponse = userService.getUserByFirstName(firstName);
        return new ApiResponse<>(userResponse);
    }

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<UserResponse> addUser(@Valid @RequestBody UserRequest userRequest) {
        UserResponse userResponse = userService.addUser(userRequest);
        return new ApiResponse<>(userResponse);
    }

    @DeleteMapping(value = "/{id}")
    public ApiResponse<Boolean> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ApiResponse<>(Boolean.TRUE);
    }

    @PutMapping(value = "/{id}")
    public ApiResponse<UserResponse> updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        UserResponse updatedUser = userService.updateUser(id, userRequest);
        return new ApiResponse<>(updatedUser);
    }

}
