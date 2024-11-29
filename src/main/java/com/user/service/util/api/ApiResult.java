package com.user.service.util.api;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ApiResult {

    private int code;

    private String message;

    public ApiResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
