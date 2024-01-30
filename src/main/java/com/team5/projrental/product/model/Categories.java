package com.team5.projrental.product.model;

import com.team5.projrental.common.exception.ErrorMessage;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Categories {
    @Min(value = 1, message = ErrorMessage.ILLEGAL_RANGE_EX_MESSAGE)
    private int mainCategory;
    @Min(value = 1, message = ErrorMessage.ILLEGAL_RANGE_EX_MESSAGE)
    private int subCategory;
}
