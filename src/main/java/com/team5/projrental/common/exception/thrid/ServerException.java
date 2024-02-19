package com.team5.projrental.common.exception.thrid;

import com.team5.projrental.common.exception.ErrorCode;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public class ServerException extends RuntimeException{
    private ErrorCode errorCode;
    private String reason;

    public ServerException(String message) {
        super(message);
        this.errorCode = Arrays.stream(ErrorCode.values()).filter(e -> e.getMessage().equals(message)).findFirst()
                .orElse(ErrorCode.SERVER_ERR_MESSAGE);
    }

    public ServerException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
