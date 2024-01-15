package com.team5.projrental.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SigninVo {
    @JsonIgnore
    private String upw;
    @JsonIgnore
    private String uid;
    private String auth;
    private int iuser;
    private String result;
    private String firebaseToken;
    private String accessToken;
}
