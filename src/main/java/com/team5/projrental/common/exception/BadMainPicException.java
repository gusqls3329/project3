package com.team5.projrental.common.exception;

import com.team5.projrental.common.exception.base.BadInformationException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public class BadMainPicException extends BadInformationException {

    private ErrorCode errorCode;
    public BadMainPicException(String message) {
        super(message);
        this.errorCode = Arrays.stream(ErrorCode.values()).filter(e -> e.getMessage().equals(message)).findFirst()
                .orElse(ErrorCode.SERVER_ERR_MESSAGE);
    }

    public BadMainPicException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

}

