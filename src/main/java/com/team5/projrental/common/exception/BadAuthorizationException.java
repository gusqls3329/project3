package com.team5.projrental.common.exception;

import com.team5.projrental.common.exception.base.BadInformationException;

public class BadAuthorizationException extends BadInformationException {
    public BadAuthorizationException(String message) {
        super(message);
    }

    public BadAuthorizationException(ErrorCode errorCode) {
        super(errorCode);
    }
}
