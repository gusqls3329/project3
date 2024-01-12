package com.team5.projrental.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignupDto {
    @JsonIgnore
    private int iuser;
    private int iaddr;
    private String addr;
    private String restAddr;
    private String uid;
    private String upw;
    private String nick;
    private String pic;
    private String phone;
    private String email;
    @JsonIgnore
    private double x;
    @JsonIgnore
    private double y;
}
