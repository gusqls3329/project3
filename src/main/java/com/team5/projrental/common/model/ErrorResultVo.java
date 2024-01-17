package com.team5.projrental.common.model;

import com.team5.projrental.common.utils.ErrorCode;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResultVo {
    private String message;
    private Integer errorCode;

}
