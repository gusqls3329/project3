package com.team5.projrental.payment.kakao.model.success;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PayApproveDto {

    private String requestUrl;

    // for Approve
    private PayApproveBodyInfo payApproveBodyInfo;

}
