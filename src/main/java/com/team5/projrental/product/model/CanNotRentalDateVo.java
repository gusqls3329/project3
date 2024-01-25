package com.team5.projrental.product.model;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CanNotRentalDateVo {
    private LocalDate rentalStartDate;
    private LocalDate rentalEndDate;
}
