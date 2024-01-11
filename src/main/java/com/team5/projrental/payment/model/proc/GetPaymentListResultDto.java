package com.team5.projrental.payment.model.proc;

import lombok.Data;

import java.time.LocalDate;

@Data
public class GetPaymentListResultDto {
    private Integer iuser;
    private String nick;
    private String storedPic;
    private String requestPic;

    private Integer ipayment;
    private Integer iproduct;
    private Integer istatus;
    private LocalDate rentalStartDate;
    private LocalDate rentalEndDate;
    private Integer rentalDuration;
    private Integer price;
    private Integer deposit;
}
