package com.team5.projrental.payment.model.proc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetInfoForCheckIproductAndIuserResult {
    private Integer istatus;
    private LocalDate rentalEndDate;
    private int iBuyer;
    private int iSeller;
}
