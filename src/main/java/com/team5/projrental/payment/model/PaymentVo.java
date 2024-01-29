package com.team5.projrental.payment.model;

import com.team5.projrental.payment.model.proc.GetPaymentListResultDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.team5.projrental.common.Const.PAYMENT_METHODS;

@Getter
@Setter
public class PaymentVo extends PaymentListVo {

    private String phone;
    private String payment; // ipayment 를 자바에서 파싱
    private String code;
    private Integer role;
    private LocalDateTime createdAt;


    public PaymentVo(Integer iuser, String nick, String userPic, Integer ipayment, Integer iproduct, String title, String prodPic,
                     Integer istatus,
                     LocalDate rentalStartDate, LocalDate rentalEndDate, Integer rentalDuration, Integer price, Integer deposit,
                     String phone, String payment, String code, Integer role,  LocalDateTime createdAt) {
        super(iuser, nick, userPic, ipayment, iproduct, prodPic, title, istatus, rentalStartDate, rentalEndDate, rentalDuration,
                price,
                deposit);
        this.phone = phone;
        this.payment = payment;
        this.code = code;
        this.role = role;
        this.createdAt = createdAt;
    }

    public PaymentVo(GetPaymentListResultDto dto) {
        super(dto.getIuser(), dto.getNick(), dto.getUserStoredPic(), dto.getIpayment(), dto.getIproduct(),
                dto.getTitle(), dto.getProdStoredPic(), dto.getIstatus(), dto.getRentalStartDate(), dto.getRentalEndDate(),
                dto.getRentalDuration(),
                dto.getPrice(), dto.getDeposit());

        this.phone = dto.getPhone();
        this.payment = PAYMENT_METHODS.get(dto.getIpaymentMethod());
        this.code = dto.getCode();
        this.role = dto.getRole();
        this.createdAt = dto.getCreatedAt();
    }
}
