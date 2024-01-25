package com.team5.projrental.common.sse.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SseResponse {
    private int iuser;
    private String code;
    private String message;
}
