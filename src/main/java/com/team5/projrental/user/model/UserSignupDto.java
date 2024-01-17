package com.team5.projrental.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

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

    @NotBlank
    @Length(max = 20)
    private String nick;

    private MultipartFile pic;

    @NotBlank
    @Pattern(regexp = "(01)\\d-\\d{3,4}-\\d{4}")
    private String phone;

    @Pattern(regexp = "\\w+@\\w+\\.\\w+(\\.\\w+)?")
    @NotBlank
    private String email;

    @NotNull
    @Range(min = 2, max = 2)
    private Integer isValid;

    @JsonIgnore
    private double x;
    @JsonIgnore
    private double y;
    @JsonIgnore
    private String chPic;

}
