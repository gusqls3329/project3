package com.team5.projrental.payment.kakao.requester;

import com.team5.projrental.payment.kakao.property.ApiPayProperty;
import com.team5.projrental.payment.kakao.model.ready.PayInfoDto;
import com.team5.projrental.payment.kakao.model.ready.PayReadyBodyInfo;
import com.team5.projrental.payment.kakao.model.ready.PayReadyDto;
import com.team5.projrental.payment.kakao.model.success.PayApproveBodyInfo;
import com.team5.projrental.payment.kakao.model.success.PayApproveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 외부 API 와 통신을 하여, 요청에대한 응답을 받는 유틸.
 */
@Component
@RequiredArgsConstructor
public class KakaoPayRequester {

    private final ApiPayProperty payProperty;

    public PayReadyDto getReadyRequest(Long id, PayInfoDto payInfoDto) {
        /**
         * partner_user_id, partner_order_id
         * 는 결제 승인 요청에서도 동일해야 한다.
         * 값은 자유지만, 주문번호와, 회원의 식별자값을 주는게 좋다.
         */
        Integer quantity = 1; // quantity = 상품 수량

        // 파라미터 세팅
        PayReadyBodyInfo payReadyBodyInfo = PayReadyBodyInfo.builder()
                .cid(payProperty.getCid())
                .partner_user_id(payInfoDto.getUserNm())
                .partner_order_id(String.valueOf(id))
                .item_name(payInfoDto.getItemName())
                .quantity(String.valueOf(quantity))
                .total_amount(String.valueOf(payInfoDto.getTotalPrice() * quantity))
                .tax_free_amount(String.valueOf(0))
                .approval_url(payProperty.getSuccessRedirectUrl())
                .fail_url(payProperty.getFailRedirectUrl())
                .cancel_url(payProperty.getCancelRedirectUrl())
                .build();


        return PayReadyDto.builder()
                .requestUrl(payProperty.getRequestUrl())
                .payReadyBodyInfo(payReadyBodyInfo)
                .build();
    }
                                                                                        // 이 파라미터는 내가 서비스에서 찾아서 넘겨줄것임.
    public PayApproveDto getApproveRequest(String tidParam, Long id, String pgToken, String paymentCode) {

        PayApproveBodyInfo payApproveBodyInfo = PayApproveBodyInfo.builder()
                .cid(payProperty.getCid())
                .tid(tidParam)
                .partner_order_id(paymentCode)
                .partner_user_id(String.valueOf(id))
                .pg_token(pgToken)
                .build();

        return PayApproveDto.builder()
                .requestUrl(payProperty.getRequestUrl())
                .payApproveBodyInfo(payApproveBodyInfo)
                .build();

    }
}
