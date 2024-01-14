package com.team5.projrental.payment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;

@Data
@Builder
public class PaymentInsDto {
    @NotNull
    @Min(0)
    private Integer iproduct;
    @NotNull
    @Min(0)
    private Integer ibuyer;
    @NotEmpty
    private String paymentMethod;
    @NotNull
    @FutureOrPresent
    private LocalDate rentalStartDate;
    @NotNull
    @FutureOrPresent
    private LocalDate rentalEndDate;
    @NotNull
    @Range(min = 50, max = 100)
    private Integer depositPer;


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
