package com.team5.projrental.payment.kakao.model.logic;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PayApproveResponseDto {
    private Amount amount; // 결제 금액 정보
    private String item_name; // 상품 명
    private String created_at; // 결제 요청 시간
    private String approved_at; // 결제 승인 시간
}
