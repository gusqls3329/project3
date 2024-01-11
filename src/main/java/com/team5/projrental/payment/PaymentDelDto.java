package com.team5.projrental.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDelDto {
    private Integer ipayment;
    private Integer iuser;
    private Integer div;
}
