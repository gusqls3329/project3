package com.team5.projrental.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
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
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int auth;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long iuser;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String result;
    @JsonIgnore // 3차 완성시 삭제
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String firebaseToken;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String accessToken;
}
