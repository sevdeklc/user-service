package com.user.service.util.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T> {

    private T data;

    private ApiResult result;

    public ApiResponse() {
    }

    public ApiResponse(T data) {
        this.data = data;
        this.result = new ApiResult(0, "success");
    }

}
