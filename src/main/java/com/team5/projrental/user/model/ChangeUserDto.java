package com.team5.projrental.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ChangeUserDto {

    @Length(max = 20)
    private String nick;
    //private MultipartFile pic;
    //private String pushpic;
    private MultipartFile pic;

    @Length(min = 8, max = 20)
    private String upw;

    @Pattern(regexp = "(01)\\d-\\d{3,4}-\\d{4}")
    private String phone;

    private String addr;

    private String restAddr;

    @Pattern(regexp = "\\w+@\\w+\\.\\w+(\\.\\w+)?")
    private String email;

    @JsonIgnore
    private String chPic;
    @JsonIgnore
    private int iuser;
    @JsonIgnore
    private double x;
    @JsonIgnore
    private double y;
}
