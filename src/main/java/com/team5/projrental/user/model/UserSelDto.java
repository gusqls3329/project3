package com.team5.projrental.user.model;

import com.team5.projrental.common.exception.ErrorMessage;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class UserSelDto {
    @NotBlank(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
    private String uid;

    @Range(min = 1)
    private int iuser;
}
