package com.team5.projrental.payment.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.Resource;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class PaymentVo extends PaymentListVo {

    private String phone;
    private String payment; // ipayment 를 자바에서 파싱
    private String code;
    private Integer role;
    private LocalDateTime createdAt;


    public PaymentVo(Integer iuser, String nick, String userPic, Integer ipayment, Integer iproduct, String prodPic,
                     Integer istatus,
                     LocalDate rentalStartDate, LocalDate rentalEndDate, Integer rentalDuration, Integer price, Integer deposit,
                     String phone, String payment, String code, Integer role,  LocalDateTime createdAt) {
        super(iuser, nick, userPic, ipayment, iproduct, prodPic, istatus, rentalStartDate, rentalEndDate, rentalDuration, price,
                deposit);
        this.phone = phone;
        this.payment = payment;
        this.code = code;
        this.role = role;
        this.createdAt = createdAt;
    }
}
