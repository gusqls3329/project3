package com.team5.projrental.common.exception;

import com.team5.projrental.common.exception.base.BadInformationException;

public class BadWordException extends BadInformationException {
    public BadWordException(String message) {
        super(message);
    }

    public BadWordException(ErrorCode errorCode) {
        super(errorCode);
    }
}
