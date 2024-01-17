package com.team5.projrental.common.exception;

import com.team5.projrental.common.exception.base.IllegalException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public class IllegalCategoryException extends IllegalException {


    private ErrorCode errorCode;
    public IllegalCategoryException(String message) {
        super(message);
        this.errorCode = Arrays.stream(ErrorCode.values()).filter(e -> e.getMessage().equals(message)).findFirst()
                .orElse(ErrorCode.SERVER_ERR_MESSAGE);
    }

    public IllegalCategoryException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

}
