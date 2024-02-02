package com.team5.projrental.user.model;

import com.team5.projrental.common.exception.ErrorMessage;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import static com.team5.projrental.common.exception.ErrorCode.*;

@Data
public class UserCheckInfoDto {

    @NotNull(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
    @Range(min = 1, max = 2, message = ErrorMessage.ILLEGAL_RANGE_EX_MESSAGE)
    private Integer div;

    @NotBlank(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
    @Length(min = 4, max = 15, message = ErrorMessage.ILLEGAL_RANGE_EX_MESSAGE)
    private String uid;

    @NotBlank(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
    @Length(max = 20, message = ErrorMessage.ILLEGAL_RANGE_EX_MESSAGE)
    private String nick;


}
