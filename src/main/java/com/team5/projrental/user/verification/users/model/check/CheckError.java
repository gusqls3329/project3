package com.team5.projrental.user.verification.users.model.check;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CheckError {
    private Integer errorType;
    private String reason;
}
