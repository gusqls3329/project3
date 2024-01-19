package com.team5.projrental.payment.model.proc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetDepositAndPriceFromProduct {
    private Integer deposit;
    private Integer price;
}
