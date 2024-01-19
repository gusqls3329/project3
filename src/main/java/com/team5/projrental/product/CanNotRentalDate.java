package com.team5.projrental.product;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CanNotRentalDate {
    private LocalDate rentalStartDate;
    private LocalDate rentalEndDate;
}
