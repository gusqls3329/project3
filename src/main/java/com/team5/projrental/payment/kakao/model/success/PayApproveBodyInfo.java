package com.team5.projrental.payment.kakao.model.success;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PayApproveBodyInfo {
    private String cid;
    private String tid;
    private String partner_order_id;
    private String partner_user_id;
    private String pg_token;
}
