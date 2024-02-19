package com.team5.projrental.payment.kakao.model.logic;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class PayApproveVo {
    private Integer totalPrice;
    private Integer tax;
    private String itemName;
    private String createdAt;
    private String approvedAt;
}
