package com.team5.projrental.payment.kakao.model.ready;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PayReadyDto {
    private String requestUrl;
    private PayReadyBodyInfo payReadyBodyInfo;

}
