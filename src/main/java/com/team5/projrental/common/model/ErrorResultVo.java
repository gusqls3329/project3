package com.team5.projrental.common.model;

import lombok.Data;

@Data
public class ErrorResultVo {
    private String message;
    private String reason;
    private Integer errorCode;

    public ErrorResultVo(String message, String reason) {
        this.message = message;
        this.reason = reason;
    }
}
