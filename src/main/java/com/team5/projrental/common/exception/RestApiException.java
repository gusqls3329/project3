package com.team5.projrental.common.exception;

public class RestApiException extends RuntimeException{
    public RestApiException(String message) {
        super(message);
    }
}
