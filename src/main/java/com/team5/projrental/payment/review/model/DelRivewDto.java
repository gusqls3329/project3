package com.team5.projrental.payment.review.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team5.projrental.common.exception.ErrorMessage;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DelRivewDto {
    @JsonIgnore
    private Long iuser;

    @JsonIgnore
    private String istatus;

    @NotNull(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
    @Min(value = 1, message = ErrorMessage.ILLEGAL_RANGE_EX_MESSAGE)
    private int ireview;
}
