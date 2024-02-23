package com.team5.projrental.user.verification.users.model.ready;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class VerificationReadyDto {
    private String resultType;
    private Success success;
    private Fail fail;
    private Long id;
    private String txid;
}
