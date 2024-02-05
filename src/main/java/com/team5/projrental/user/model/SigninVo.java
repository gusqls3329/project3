package com.team5.projrental.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SigninVo {
    @JsonIgnore
    private String upw;
    @JsonIgnore
    private String uid;
    private String auth;
    private int iauth;
    private int iuser;
    private String result;
    private String firebaseToken;
    private String accessToken;
}
