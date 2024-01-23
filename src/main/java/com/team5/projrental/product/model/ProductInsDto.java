package com.team5.projrental.product.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team5.projrental.common.exception.ErrorMessage;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductInsDto {
//    @NotNull
    @JsonIgnore
    private Integer iuser;
    @NotBlank(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
    private String title;
    @NotBlank(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
    private String contents;
    @NotBlank(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
    private String addr;
    @NotBlank(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
    private String restAddr;
    @JsonIgnore
    private String mainPic;
    @JsonIgnore
    private List<String> pics;
    @NotNull(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
    @Range(min = 100, max = Integer.MAX_VALUE, message = ErrorMessage.ILLEGAL_RANGE_EX_MESSAGE)
    private Integer price;
    @NotNull(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
    @Range(min = 100, max = Integer.MAX_VALUE, message = ErrorMessage.ILLEGAL_RANGE_EX_MESSAGE)
    private Integer rentalPrice;
    @NotNull(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
    @Range(min = 50, max = 100, message = ErrorMessage.ILLEGAL_RANGE_EX_MESSAGE)
    private Integer depositPer;
    @NotNull(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
    @PastOrPresent(message = ErrorMessage.ILLEGAL_DATE_EX_MESSAGE)
    private LocalDate buyDate;
    @FutureOrPresent(message = ErrorMessage.ILLEGAL_DATE_EX_MESSAGE)
    private LocalDate rentalStartDate;
    @FutureOrPresent(message = ErrorMessage.ILLEGAL_DATE_EX_MESSAGE)
    private LocalDate rentalEndDate;
    @NotNull(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
    @Min(value = 1, message = ErrorMessage.ILLEGAL_RANGE_EX_MESSAGE)
    private Integer icategory;


}
