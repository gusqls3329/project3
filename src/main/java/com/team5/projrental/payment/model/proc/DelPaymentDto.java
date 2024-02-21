package com.team5.projrental.payment.model.proc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DelPaymentDto {
    private Integer ipayment;
    private Integer istatus;
    private Long loginIuser;

    public DelPaymentDto(Integer ipayment, Integer istatus) {
        this.ipayment = ipayment;
        this.istatus = istatus;

    }
}
