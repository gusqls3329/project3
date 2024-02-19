package com.team5.projrental.payment.kakao.model.logic;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PayReadyResponseDto {
    private String tid;
    private String next_redirect_pc_url;
    private String created_at;
}
