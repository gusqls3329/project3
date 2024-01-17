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

@RestControllerAdvice
@Slf4j
public class ExceptionResolver {


    // 400
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
