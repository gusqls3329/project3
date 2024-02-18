package com.team5.projrental.entities.embeddable;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Embeddable
public class RentalDates {

    private LocalDate rentalStartDate;
    private LocalDate rentalEndDate;

    protected RentalDates() {

    }

}
