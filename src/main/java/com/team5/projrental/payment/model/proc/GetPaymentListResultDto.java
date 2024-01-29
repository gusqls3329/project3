package com.team5.projrental.payment.model.proc;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class GetPaymentListResultDto {
    private Integer iuser;
    private String nick;
    private String userStoredPic;

    private Integer ipayment;
    private Integer iproduct;
    private String title;
    private String prodStoredPic;
    private Integer istatus;
    private Integer ipaymentMethod;
    private LocalDate rentalStartDate;
    private LocalDate rentalEndDate;
    private Integer rentalDuration;
    private Integer price;
    private Integer deposit;
    // for a payment
    private String phone;
    private String code;
    private Integer role;
    private LocalDateTime createdAt;
}
