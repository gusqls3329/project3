package com.team5.projrental.common.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResultVo {
    private String message;
    private Integer errorCode;

}
