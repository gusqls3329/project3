package com.team5.projrental.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team5.projrental.entities.enums.Auth;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FindUidVo {
    @JsonIgnore
    private int iuser;
    private String uid;
    private int auth;
}
