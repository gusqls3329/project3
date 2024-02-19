package com.team5.projrental.payment.kakao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team5.projrental.common.exception.ErrorCode;
import com.team5.projrental.common.exception.thrid.ServerException;
import com.team5.projrental.common.security.AuthenticationFacade;
import com.team5.projrental.entities.PaymentDetail;
import com.team5.projrental.entities.enums.PaymentDetailCategory;
import com.team5.projrental.payment.kakao.model.logic.PayReadyResponseDto;
import com.team5.projrental.payment.kakao.model.logic.PayReadyVo;
import com.team5.projrental.payment.kakao.model.ready.PayInfoDto;
import com.team5.projrental.payment.kakao.model.ready.PayReadyDto;
import com.team5.projrental.payment.kakao.property.ApiPayProperty;
import com.team5.projrental.payment.kakao.repository.PaymentDetailRepository;
import com.team5.projrental.payment.kakao.requester.KakaoPayRequester;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;

import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class KakaoPayService {

    private final KakaoPayRequester requester;
    private final AuthenticationFacade facade;
    private final ApiPayProperty property;
    private final ObjectMapper om;
    private final PaymentDetailRepository repository;

    @Transactional
    public PayReadyVo ready(PayInfoDto dto) {

        // 전체 금액 계산
        dto.setTotalPrice(((int) ChronoUnit.DAYS.between(dto.getRentalStartDate(),
                dto.getRentalEndDate())) + 1);

        PayReadyDto payReadyDto = requester.getReadyRequest((long) facade.getLoginUserPk(), dto);

        String response = RestClient.create(payReadyDto.getRequestUrl())
                .post()
                .header(property.getHeaderKey(), property.getHeaderValue())
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(String.class);
        PayReadyResponseDto responseDto;
        try {
            responseDto = om.readValue(response, PayReadyResponseDto.class);
        } catch (JsonProcessingException e) {
            throw new ServerException(ErrorCode.SERVER_ERR_MESSAGE, "Api 통신과정에서 에러가 발생했습니다.");
        }

        repository.save(PaymentDetail.builder()
                .tid(responseDto.getTid())
                .category(PaymentDetailCategory.KAKAO_PAY)
                .build());
        return PayReadyVo.builder()
                .nextRequestUrl(responseDto.getNext_redirect_pc_url())
                .build();

    }


}
