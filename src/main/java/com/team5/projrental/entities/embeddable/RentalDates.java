package com.team5.projrental.entities.embeddable;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Embeddable
public class RentalDates {

    private LocalDateTime rentalStartDate;
    private LocalDateTime rentalEndDate;

    protected RentalDates() {

    }

}
