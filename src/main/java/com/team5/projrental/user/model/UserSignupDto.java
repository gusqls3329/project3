package com.team5.projrental.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team5.projrental.common.exception.ErrorMessage;
import com.team5.projrental.entities.enums.JoinStatus;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

import static com.team5.projrental.common.exception.ErrorCode.CAN_NOT_BLANK_EX_MESSAGE;

@Getter
@Setter
public class UserSignupDto {


    @JsonIgnore
    private int iuser;

    @NotBlank(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
    private String addr;

    @NotBlank(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
    @Length(max = 30)
    private String restAddr;

    @NotBlank(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
    @Pattern(regexp =  "^\\w{4,15}$", message = ErrorMessage.NO_SUCH_USER_EX_MESSAGE)
    @Length(min = 4, max = 15, message = ErrorMessage.ILLEGAL_RANGE_EX_MESSAGE)
    private String uid;

    @NotBlank(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
    @Length(min = 8, max = 20, message = ErrorMessage.ILLEGAL_RANGE_EX_MESSAGE)
    private String upw;

    @NotBlank(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
    @Pattern(regexp = "^\\S{0,20}$", message = ErrorMessage.NO_SUCH_USER_EX_MESSAGE)
    @Length(max = 20, message = ErrorMessage.ILLEGAL_RANGE_EX_MESSAGE)
    private String nick;

    @JsonIgnore
    private MultipartFile pic;

    @NotBlank(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
    @Pattern(regexp = "(0)\\d{2}-\\d{3,4}-\\d{4}", message = ErrorMessage.BAD_INFO_EX_MESSAGE)
    private String phone;

    @Length(max = 30, message = ErrorMessage.ILLEGAL_RANGE_EX_MESSAGE)
    @Pattern(regexp = "\\w+@\\w+\\.\\w+(\\.\\w+)?", message = ErrorMessage.BAD_INFO_EX_MESSAGE)
    @NotBlank(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
    private String email;

    @NotNull(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
    @Range(min = 2, max = 2, message = ErrorMessage.ILLEGAL_RANGE_EX_MESSAGE)
    private Integer isValid;

    //uuid : 본인인증 (조인테이블)
    private Long iverificationInfo;

    @JsonIgnore
    private double x;
    @JsonIgnore
    private double y;
    @JsonIgnore
    private String chPic;
    @JsonIgnore
    private int iauth;
    @JsonIgnore
    private int cash;
    @JsonIgnore
    private JoinStatus joinStatus;
}
