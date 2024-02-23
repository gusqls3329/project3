package com.team5.projrental.product.model.jpa;

import com.team5.projrental.entities.Stock;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class ActivatedStock {
    private LocalDateTime date;
    private List<Stock> activatedStock;
}
