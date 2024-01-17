package com.team5.projrental.user.model;


import jakarta.validation.constraints.NotBlank;
import com.team5.projrental.common.exception.ErrorMessage;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class SigninDto {
    @NotBlank(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
    @Length(min = 8, max = 15, message = ErrorMessage.ILLEGAL_RANGE_EX_MESSAGE)
    private String uid;

    @NotBlank(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
    @Length(min = 8, max = 20, message = ErrorMessage.ILLEGAL_RANGE_EX_MESSAGE)
    private String upw;
}
