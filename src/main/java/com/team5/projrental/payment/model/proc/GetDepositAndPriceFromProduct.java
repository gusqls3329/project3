package com.team5.projrental.payment.model.proc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetDepositAndPriceFromProduct {
    private Integer rentalPrice;
    private Integer deposit;
    private Integer price;
    private Integer inventory;
    private LocalDate rentalStartDate;
    private LocalDate rentalEndDate;
}
