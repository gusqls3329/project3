package com.team5.projrental.common.exception;

import com.team5.projrental.common.exception.base.BadInformationException;
import com.team5.projrental.common.utils.ErrorCode;

import java.util.Arrays;

public class BadDivInformationException extends BadInformationException {

    private ErrorCode errorCode;
    public BadDivInformationException(String message) {
        super(message);
        this.errorCode = Arrays.stream(ErrorCode.values()).filter(e -> e.getMessage().equals(message)).findFirst()
                .orElse(ErrorCode.SERVER_ERR_MESSAGE);
    }

    public BadDivInformationException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
