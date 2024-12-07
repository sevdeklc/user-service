package com.user.service.util;

import lombok.Getter;

@Getter
public enum ErrorCode {

    USER_NOT_FOUND(10000, "error.user.not_found");

    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
