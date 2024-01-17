package com.team5.projrental.common.exception.base;

import com.team5.projrental.common.exception.ErrorCode;

import java.util.Arrays;

public class NoSuchDataException extends RuntimeException{


    private ErrorCode errorCode;
    public NoSuchDataException(String message) {
        super(message);
        this.errorCode = Arrays.stream(ErrorCode.values()).filter(e -> e.getMessage().equals(message)).findFirst()
                .orElse(ErrorCode.SERVER_ERR_MESSAGE);
    }

    public NoSuchDataException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
