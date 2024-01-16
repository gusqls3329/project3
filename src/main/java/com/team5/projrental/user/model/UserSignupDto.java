package com.team5.projrental.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class UserSignupDto {
    @JsonIgnore
    private int iuser;

    @NotBlank
    private String addr;

    @NotBlank
    private String restAddr;

    @NotBlank
    @Length(min = 8, max = 15)
    private String uid;

    @NotBlank
    @Length(min = 8, max = 20)
    private String upw;

    @Length(max = 20)
    private String nick;

    @NotBlank
    private String pic;

    @NotBlank
    private String phone;

    @NotBlank
    private String email;
    @JsonIgnore
    private double x;
    @JsonIgnore
    private double y;
}
