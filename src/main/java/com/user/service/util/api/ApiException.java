package com.user.service.util.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiException extends RuntimeException {

    private final int errorCode;
    private String errorKey;

    public ApiException(int errorCode, String errorKey) {
        this.errorCode = errorCode;
        this.errorKey = errorKey;
    }

}
