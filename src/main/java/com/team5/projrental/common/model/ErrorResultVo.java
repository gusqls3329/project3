package com.team5.projrental.common.model;

import lombok.Data;

@Data
public class ErrorResultVo {
    private String message;
    private Integer errorCode;

    public ErrorResultVo(String message) {
        this.message = message;

    }
}
