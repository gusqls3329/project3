package com.team5.projrental.payment.kakao.model.logic;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Amount {
    private int total; // 총 결제 금액
    private int tax_free; // 비과세 금액
    private int tax; // 부가세 금액
}
