package com.team5.projrental.payment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team5.projrental.common.exception.ErrorMessage;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentInsDto {
    @NotNull(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
    @Min(value = 1, message = ErrorMessage.ILLEGAL_RANGE_EX_MESSAGE)
    private Integer iproduct;
//    @NotNull
//    @Min(0)
    @JsonIgnore
    private Integer ibuyer;
    @NotEmpty(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
    private String paymentMethod;
    @NotNull(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
    @FutureOrPresent(message = ErrorMessage.RENTAL_DATE_MUST_BE_BEFORE_THAN_TODAY_EX_MESSAGE)
    private LocalDate rentalStartDate;
    @NotNull(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
    @FutureOrPresent(message = ErrorMessage.RENTAL_DATE_MUST_BE_BEFORE_THAN_TODAY_EX_MESSAGE)
    private LocalDate rentalEndDate;
//    @NotNull(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
//    @Range(min = 50, max = 100, message = ErrorMessage.ILLEGAL_RANGE_EX_MESSAGE)
//    private Integer depositPer;


    //
    @JsonIgnore
    private Integer ipayment;
    @JsonIgnore
    private Integer rentalDuration;
    @JsonIgnore
    private Integer ipaymentMethod;
    @JsonIgnore
    private Integer price;
    @JsonIgnore
    private String code;
    @JsonIgnore
    private Integer deposit;


}
