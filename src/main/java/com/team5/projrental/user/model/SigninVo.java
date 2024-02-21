package com.team5.projrental.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team5.projrental.entities.enums.Auth;
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
    private Auth auth;
    private Long iuser;
    private String result;
    private String firebaseToken;
    private String accessToken;
}
