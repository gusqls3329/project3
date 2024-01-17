package com.team5.projrental.user.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import com.team5.projrental.common.exception.ErrorMessage;

@Data
public class FindUidDto {
    @NotBlank(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
    @Pattern(regexp = "(01)\\d-\\d{3,4}-\\d{4}", message = ErrorMessage.BAD_INFO_EX_MESSAGE)
    private String phone;
}
