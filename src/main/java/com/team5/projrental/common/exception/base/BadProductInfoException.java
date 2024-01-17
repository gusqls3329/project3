package com.team5.projrental.common.exception.base;

import com.team5.projrental.common.exception.ErrorCode;

public class BadProductInfoException extends BadInformationException{
    public BadProductInfoException(String message) {
        super(message);
    }

    public BadProductInfoException(ErrorCode errorCode) {
        super(errorCode);
    }
}
