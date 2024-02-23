package com.team5.projrental.user.verification.users.model.ready;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class VerificationReadyVo {
    private String resultType;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Success success;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Fail fail;
    private Long id;
}
