package com.team5.projrental.payment.model.entity;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class PaymentEntity {
    private Integer ipayment;
    private Integer ibuyer;
    private Integer ipaymentMethod;
    private Integer istatus;
    private LocalDate rentalStartDate;
    private LocalDate rentalEndDate;
    private Integer rentalDuration;
    private Integer price;
    private String code;
    private LocalDateTime createdAt;
}
