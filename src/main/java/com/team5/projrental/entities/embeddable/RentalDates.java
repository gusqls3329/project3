package com.team5.projrental.entities.embeddable;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class RentalDates {

    private LocalDate rentalStartDate;
    private LocalDate rentalEndDate;

}
