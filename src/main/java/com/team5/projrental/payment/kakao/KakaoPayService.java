package com.team5.projrental.payment.kakao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.team5.projrental.common.exception.ErrorCode;
import com.team5.projrental.common.exception.thrid.ClientException;
import com.team5.projrental.common.exception.thrid.ServerException;
import com.team5.projrental.common.security.AuthenticationFacade;
import com.team5.projrental.entities.PaymentDetail;
import com.team5.projrental.entities.QPaymentDetail;
import com.team5.projrental.entities.enums.PaymentDetailCategory;
import com.team5.projrental.entities.inheritance.QUsers;
import com.team5.projrental.payment.kakao.model.logic.PayApproveResponseDto;
import com.team5.projrental.payment.kakao.model.logic.PayApproveVo;
import com.team5.projrental.payment.kakao.model.logic.PayReadyResponseDto;
import com.team5.projrental.payment.kakao.model.logic.PayReadyVo;
import com.team5.projrental.payment.kakao.model.ready.PayInfoDto;
import com.team5.projrental.payment.kakao.model.ready.PayReadyDto;
import com.team5.projrental.payment.kakao.model.success.PayApproveBodyInfo;
import com.team5.projrental.payment.kakao.model.success.PayApproveDto;
import com.team5.projrental.payment.kakao.property.ApiPayProperty;
import com.team5.projrental.payment.kakao.repository.PaymentDetailRepository;
import com.team5.projrental.payment.kakao.requester.KakaoPayRequester;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class KakaoPayService {

    private final KakaoPayRequester requester;
    private final AuthenticationFacade facade;
    private final ApiPayProperty property;
    private final ObjectMapper om;
    private final PaymentDetailRepository repository;
    private final JPAQueryFactory queryFactory;

    @Transactional
    public PayReadyVo ready(PayInfoDto dto) {

        // 전체 금액 계산
        dto.setTotalPrice(((int) ChronoUnit.DAYS.between(dto.getRentalStartDate(),
                dto.getRentalEndDate())) + 1);

        PayReadyDto payReadyDto = requester.getReadyRequest((long) facade.getLoginUserPk(), dto);

        RestClient restClient = RestClient.builder()
                .baseUrl(payReadyDto.getRequestUrl())
                .build();

        String response = restClient
                .post()
                .header(property.getHeaderKey(), property.getHeaderValue())
                .body(payReadyDto.getPayReadyBodyInfo())
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(String.class);
        PayReadyResponseDto responseDto;
        try {
            responseDto = om.readValue(response, PayReadyResponseDto.class);
        } catch (JsonProcessingException e) {
            throw new ServerException(ErrorCode.SERVER_ERR_MESSAGE, "Api 통신과정에서 에러가 발생했습니다.");
        }
        String uuid = UUID.randomUUID().toString();
        repository.save(PaymentDetail.builder()
                .id(uuid)
                .tid(responseDto.getTid())
                .category(PaymentDetailCategory.KAKAO_PAY)
                .build());
        return PayReadyVo.builder()
                .uuid(uuid)
                .nextRequestUrl(responseDto.getNext_redirect_pc_url())
                .build();

    }

    public PayApproveVo approve(String pgToken, String uuid) {
        Long loginUserPk = (long) facade.getLoginUserPk();
        QPaymentDetail paymentDetail = QPaymentDetail.paymentDetail;
        List<String> result = queryFactory.select(paymentDetail.tid)
                .from(paymentDetail)
                .where(paymentDetail.id.eq(uuid))
                .fetch();

        if (result.size() > 1) throw new ServerException(ErrorCode.SERVER_ERR_MESSAGE, "uuid 가 절대값이 아님, 문의 필요");
        String tid;
        try {
            tid = result.get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new ServerException(ErrorCode.SERVER_ERR_MESSAGE, "결제 요청 기록이 없습니다.");
        }
        PayApproveDto approveRequest = requester.getApproveRequest(tid, loginUserPk, pgToken);

        RestClient restClient = RestClient.builder()
                .baseUrl(approveRequest.getRequestUrl())
                .build();

        PayApproveBodyInfo payApproveBodyInfo = approveRequest.getPayApproveBodyInfo();
        RestClient.RequestBodySpec requestBodySpec = restClient
                .post()
                .header(property.getHeaderKey(), property.getHeaderValue())
                .body(payApproveBodyInfo)
                .contentType(MediaType.APPLICATION_JSON);
        RestClient.ResponseSpec retrieve = requestBodySpec.retrieve();
        String response = retrieve.body(String.class);


        PayApproveResponseDto payApproveResponseDto;
        try {
            payApproveResponseDto = om.readValue(response, PayApproveResponseDto.class);
        } catch (JsonProcessingException e) {
            throw new ClientException(ErrorCode.BAD_INFO_EX_MESSAGE);
        }


        return PayApproveVo.builder()
                .totalPrice(payApproveResponseDto.getAmount().getTotal())
                .tax(payApproveResponseDto.getAmount().getTax())
                .itemName(payApproveResponseDto.getItem_name())
                .createdAt(payApproveResponseDto.getCreated_at())
                .approvedAt(payApproveResponseDto.getApproved_at())
                .build();

    }
}
