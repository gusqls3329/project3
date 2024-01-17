package com.team5.projrental.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team5.projrental.common.exception.ErrorMessage;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import static com.team5.projrental.common.exception.ErrorCode.CAN_NOT_BLANK_EX_MESSAGE;

@Getter
@Setter
public class UserSignupDto {
    @JsonIgnore
    private int iuser;

    @NotBlank(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
    private String addr;

    @NotBlank(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
    private String restAddr;

    @NotBlank(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
    @Length(min = 8, max = 15)
    private String uid;

    @NotBlank(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
    @Length(min = 8, max = 20)
    private String upw;

    @NotBlank(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
    @Length(max = 20)
    private String nick;

    private MultipartFile pic;

    @NotBlank(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
    @Pattern(regexp = "(01)\\d-\\d{3,4}-\\d{4}")
    private String phone;

    @Pattern(regexp = "\\w+@\\w+\\.\\w+(\\.\\w+)?")
    @NotBlank(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
    private String email;

    @NotNull(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
    @Range(min = 2, max = 2)
    private Integer isValid;

    @JsonIgnore
    private double x;
    @JsonIgnore
    private double y;
    @JsonIgnore
    private String chPic;

}
