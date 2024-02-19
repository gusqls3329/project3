package com.team5.projrental.payment.kakao.model.logic;

import com.team5.projrental.payment.kakao.model.ready.PayReadyBodyInfo;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PayReadyVo {
    private String nextRequestUrl;

}
