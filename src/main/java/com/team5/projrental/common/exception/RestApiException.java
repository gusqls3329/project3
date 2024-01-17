package com.team5.projrental.common.exception;

import com.team5.projrental.common.utils.ErrorCode;

import java.util.Arrays;

public class RestApiException extends RuntimeException{
    private ErrorCode errorCode;
    public RestApiException(String message) {
        super(message);
        this.errorCode = Arrays.stream(ErrorCode.values()).filter(e -> e.getMessage().equals(message)).findFirst()
                .orElse(ErrorCode.SERVER_ERR_MESSAGE);
    }

    public RestApiException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
