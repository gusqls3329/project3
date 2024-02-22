package com.team5.projrental.user.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.team5.projrental.entities.enums.Auth;
import com.team5.projrental.entities.enums.UserStatus;
import lombok.Data;

@Data
public class SelUserVo {
    private int y;
    private int x;
    private String addr;
    private String restAddr;
    private String nick;
    private String storedPic;
    private String phone;
    private String email;
    private int rating;
    private int auth;
    private int istatus;
    //

}
