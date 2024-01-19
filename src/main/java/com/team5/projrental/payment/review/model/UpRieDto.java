package com.team5.projrental.payment.review.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team5.projrental.common.exception.ErrorMessage;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpRieDto {
    private int ireview;


    private String contents;

    @Max(value = 5, message = ErrorMessage.ILLEGAL_RANGE_EX_MESSAGE)
    private Integer rating;
}
