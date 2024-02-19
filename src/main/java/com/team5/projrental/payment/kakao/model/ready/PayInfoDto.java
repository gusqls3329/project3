package com.team5.projrental.payment.kakao.model.ready;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PayInfoDto {
    @JsonIgnore
    private Integer totalPrice;

    private Integer deposit;
    private String itemName;

    private Integer pricePerDay;
    private LocalDate rentalStartDate;
    private LocalDate rentalEndDate;


}
