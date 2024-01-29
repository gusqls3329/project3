package com.team5.projrental.payment.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class PaymentListVo {
    private Integer iuser;
    private String nick;
    private String userPic;

    private Integer ipayment;
    private Integer iproduct;
    private String title;
    private String prodMainPic;
    private Integer istatus;
    private LocalDate rentalStartDate;
    private LocalDate rentalEndDate;
    private Integer rentalDuration;
    private Integer price;
    private Integer deposit;

}
