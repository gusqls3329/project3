package com.team5.projrental.user.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.team5.projrental.entities.enums.Auth;
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
    private Auth auth;

    //

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long compCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String compNm;
}
