package com.team5.projrental.user.verification.model.check;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CheckRequestDto {
    private String txId;
    private String sessionKey;
}
