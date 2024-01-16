package com.team5.projrental.common;

import com.team5.projrental.common.exception.*;
import com.team5.projrental.common.model.ErrorResultVo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionResolver {


    // 400
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResultVo resolve(BadInformationException e) {
        return new ErrorResultVo(e.getMessage());
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResultVo resolve(NoSuchDataException e) {
        return new ErrorResultVo(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResultVo resolve(IllegalException e) {
        return new ErrorResultVo(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResultVo resolve(RestApiException e) {
        return new ErrorResultVo(e.getMessage());
    }

    // 500
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResultVo resolve(RuntimeException e) {
        return new ErrorResultVo(e.getMessage());
    }
}
