package com.team5.projrental.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ChangeUserDto {
    private String nick;
    //private MultipartFile pic;
    //private String pushpic;
    private String pic;
    private String upw;
    private String phone;
    private int iaddr;
    private String restAddr;
    private String email;
    @JsonIgnore
    private int iuser;
}
