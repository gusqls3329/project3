package com.team5.projrental.payment.review.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team5.projrental.common.exception.ErrorMessage;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

@Data
public class RivewDto {
    @JsonIgnore
    private Long iuser;

    @NotNull(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
    @Min(value = 1, message = ErrorMessage.ILLEGAL_RANGE_EX_MESSAGE)
    private Integer ipayment;


    @Length(max = 2000)
    private String contents;

    // 0점은 불가능
    @Range(min = 1, max = 5, message = ErrorMessage.ILLEGAL_RANGE_EX_MESSAGE)
    private Integer rating;

    @JsonIgnore
    private Long ireview;

}
