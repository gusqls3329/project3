package com.team5.projrental.payment.review.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team5.projrental.common.exception.ErrorMessage;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RivewDto {
    @JsonIgnore
    private int iuser;

    @NotEmpty(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
    @Min(value = 1, message = ErrorMessage.ILLEGAL_RANGE_EX_MESSAGE)
    private int ipayment;


    private String contents;
    private Integer rating;
}
