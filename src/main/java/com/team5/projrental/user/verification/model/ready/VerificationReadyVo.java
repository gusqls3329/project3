package com.team5.projrental.user.verification.model.ready;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class VerificationReadyVo {
    private String resultType;
    private String uuid;
}
