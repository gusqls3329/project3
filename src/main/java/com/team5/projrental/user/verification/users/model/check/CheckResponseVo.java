package com.team5.projrental.user.verification.users.model.check;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CheckResponseVo {
    @JsonIgnore
    private Long id;
    private String name;
    private String birthday;
    private String gender;
    private String nationality;
}
