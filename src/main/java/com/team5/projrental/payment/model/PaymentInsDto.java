package com.team5.projrental.payment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @NotNull
    @Min(1)
    private Integer iproduct;
//    @NotNull
//    @Min(0)
    @JsonIgnore
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
