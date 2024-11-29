package com.user.service.domain.dto.request;

import com.user.service.domain.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

    @NotNull
    private String firstName;

    private String lastName;

    @Email
    private String email;

    private Gender gender;

}
