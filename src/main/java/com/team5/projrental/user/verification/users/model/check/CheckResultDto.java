package com.team5.projrental.user.verification.users.model.check;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckResultDto {
    private String resultType;
    private CheckSuccess success;
    private CheckError error;
}
