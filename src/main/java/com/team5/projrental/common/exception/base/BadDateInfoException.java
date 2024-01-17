package com.team5.projrental.common.exception.base;

import com.team5.projrental.common.exception.ErrorCode;

import java.util.Arrays;

public class BadDateInfoException extends BadInformationException{

    private ErrorCode errorCode;
    public BadDateInfoException(String message) {
        super(message);
        this.errorCode = Arrays.stream(ErrorCode.values()).filter(e -> e.getMessage().equals(message)).findFirst()
                .orElse(ErrorCode.SERVER_ERR_MESSAGE);
    }

    public BadDateInfoException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
