package com.team5.projrental.common;

import com.team5.projrental.common.exception.*;
import com.team5.projrental.common.exception.base.BadInformationException;
import com.team5.projrental.common.exception.base.IllegalException;
import com.team5.projrental.common.exception.base.NoSuchDataException;
import com.team5.projrental.common.model.ErrorResultVo;
import com.team5.projrental.common.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.Arrays;

@RestControllerAdvice
@Slf4j
public class ExceptionResolver {


    // 400
    @ExceptionHandler
    public ResponseEntity<ErrorResultVo> resolve(HandlerMethodValidationException eBase) {
        StringBuilder sb = new StringBuilder();
        eBase.getAllErrors().forEach(e1 -> {
            sb.append(e1.getDefaultMessage());
            log.debug("error message = {}", e1);
        });
        String errorMessage = sb.toString();
        int errorCode = Arrays.stream(ErrorCode.values()).filter(e -> e.getMessage().equals(message)).findFirst()
                .orElse(ErrorCode.SERVER_ERR_MESSAGE).getCode();
        return ResponseEntity.status(errorCode)
                .body(ErrorResultVo.builder().errorCode(errorCode)
//                        .message(e.getErrorCode().getMessage()).build());
                        .message(errorMessage).build());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResultVo> resolve(BadInformationException e) {
        log.debug("error message", e);
        return ResponseEntity.status(e.getErrorCode().getCode())
                .body(ErrorResultVo.builder().errorCode(e.getErrorCode().getCode())
//                        .message(e.getErrorCode().getMessage()).build());
                        .message(e.getMessage()).build());

    }


    @ExceptionHandler
    public ResponseEntity<ErrorResultVo> resolve(NoSuchDataException e) {
        log.debug("error message", e);
        return ResponseEntity.status(e.getErrorCode().getCode())
                .body(ErrorResultVo.builder().errorCode(e.getErrorCode().getCode())
//                        .message(e.getErrorCode().getMessage()).build());
                        .message(e.getMessage()).build());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResultVo> resolve(IllegalException e) {
        log.debug("error message", e);
        return ResponseEntity.status(e.getErrorCode().getCode())
                .body(ErrorResultVo.builder().errorCode(e.getErrorCode().getCode())
//                        .message(e.getErrorCode().getMessage()).build());
                        .message(e.getMessage()).build());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResultVo> resolve(RestApiException e) {
        log.debug("error message", e);
        return ResponseEntity.status(e.getErrorCode().getCode())
                .body(ErrorResultVo.builder().errorCode(e.getErrorCode().getCode())
//                        .message(e.getErrorCode().getMessage()).build());
                        .message(e.getMessage()).build());
    }

    // 500
    @ExceptionHandler
    public ResponseEntity<ErrorResultVo> resolve(RuntimeException e) {
        log.debug("error message", e);
        return ResponseEntity.status(500)
                .body(ErrorResultVo.builder().errorCode(500)
                        .message(ErrorCode.SERVER_ERR_MESSAGE.getMessage()).errorCode(500).build());

    }
}
