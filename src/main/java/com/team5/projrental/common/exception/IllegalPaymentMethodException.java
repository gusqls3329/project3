package com.team5.projrental.common.exception;

import com.team5.projrental.common.exception.base.IllegalException;
import com.team5.projrental.common.utils.ErrorCode;

import java.util.Arrays;

public class IllegalPaymentMethodException extends IllegalException {


    private ErrorCode errorCode;
    public IllegalPaymentMethodException(String message) {
        super(message);
        this.errorCode = Arrays.stream(ErrorCode.values()).filter(e -> e.getMessage().equals(message)).findFirst()
                .orElse(ErrorCode.SERVER_ERR_MESSAGE);
    }

    public IllegalPaymentMethodException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
