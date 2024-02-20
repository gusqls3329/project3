package com.team5.projrental.user.verification.users.model.check;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CheckResponseVo {
    private String uuid;
    private String name;
    private String birthday;
    private String gender;
    private String nationality;
}
