package com.team5.projrental.user.model;

import com.team5.projrental.entities.enums.Auth;
import lombok.Data;

@Data
public class UserSignupProcDto {
    private Long iuser;
    private String uid;
    private String upw;
    private String nm;
    private String pic;
    private String providerType;
    private Auth auth;
}