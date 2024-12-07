package com.user.service.exception;

import com.user.service.util.ErrorCode;
import com.user.service.util.api.ApiException;

public class UserNotFoundException extends ApiException {

    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND.getCode(), ErrorCode.USER_NOT_FOUND.getMessage());
    }

}
