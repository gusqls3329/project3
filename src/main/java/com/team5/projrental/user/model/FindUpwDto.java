package com.team5.projrental.user.model;

import com.team5.projrental.common.exception.ErrorMessage;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindUpwDto {
    @NotBlank(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
    @Pattern(regexp = "(01)\\d-\\d{3,4}-\\d{4}", message = ErrorMessage.BAD_INFO_EX_MESSAGE)
    private String phone;

    @NotBlank(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
    @Pattern(regexp =  "^\\w{4,15}$", message = ErrorMessage.NO_SUCH_USER_EX_MESSAGE)
    @Length(min = 4, max = 15, message = ErrorMessage.ILLEGAL_RANGE_EX_MESSAGE)
    private String uid;

    @NotBlank(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
    @Length(min = 8, max = 20, message = ErrorMessage.ILLEGAL_RANGE_EX_MESSAGE)
    private String upw;
}
