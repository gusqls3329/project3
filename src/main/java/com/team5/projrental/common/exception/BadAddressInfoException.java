package com.team5.projrental.common.exception;

import com.team5.projrental.common.exception.base.BadInformationException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public class BadAddressInfoException extends BadInformationException {
    private ErrorCode errorCode;
    public BadAddressInfoException(String message) {
        super(message);
        this.errorCode = Arrays.stream(ErrorCode.values()).filter(e -> e.getMessage().equals(message)).findFirst()
                .orElse(ErrorCode.SERVER_ERR_MESSAGE);
    }

    public BadAddressInfoException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

}
