package com.team5.projrental.product.model.proc;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CanNotRentalDateDto {
    private int iproduct;
    private LocalDate refStartDate;
    private LocalDate refEndDate;
}
