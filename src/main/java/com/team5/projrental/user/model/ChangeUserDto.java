package com.team5.projrental.user.model;

import lombok.Data;

@Data
public class ChangeUserDto {
    private String nick;
    private String pic;
    private String upw;
    private String phone;
    private int iaddr;
    private String restAddr;
    private String email;
    private int iuser;
}
