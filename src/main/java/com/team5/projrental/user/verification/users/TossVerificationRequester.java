package com.team5.projrental.user.verification.users;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team5.projrental.common.exception.ErrorCode;
import com.team5.projrental.common.exception.thrid.ClientException;
import com.team5.projrental.entities.VerificationInfo;
import com.team5.projrental.user.verification.users.model.VerificationUserInfo;
import com.team5.projrental.user.verification.users.model.check.CheckRequestDto;
import com.team5.projrental.user.verification.users.model.check.CheckResponseVo;
import com.team5.projrental.user.verification.users.model.check.CheckResultDto;
import com.team5.projrental.user.verification.users.model.ready.VerificationReadyResponse;
import com.team5.projrental.user.verification.users.model.ready.VerificationReadyDto;
import com.team5.projrental.user.verification.users.model.ready.VerificationRequestDto;
import com.team5.projrental.user.verification.users.properties.TossVerificationProperties;
import im.toss.cert.sdk.TossCertSession;
import im.toss.cert.sdk.TossCertSessionGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@Slf4j
@RequiredArgsConstructor
public class TossVerificationRequester {

    private final TossCertSessionGenerator generator = new TossCertSessionGenerator();

    private final TossVerificationProperties tossVerificationProperties;
    private final ObjectMapper om;
//    private final TossVerificationRepository repository;


    public VerificationReadyDto verificationRequest(VerificationUserInfo userInfo) {

        // toss 에 전달할 Dto 생성
        VerificationRequestDto dto = encode(userInfo);

        // 요청
        RestClient restClient = RestClient.builder()
                .baseUrl(tossVerificationProperties.getReadyRequestUrl())
                .build();

        String responseJSON = restClient.post()
                .header(tossVerificationProperties.getTokenKey(),
                        tossVerificationProperties.getTokenType() + tossVerificationProperties.getAccessToken())
                .body(dto)
                .contentType(MediaType.APPLICATION_JSON).retrieve().body(String.class);

        VerificationReadyResponse verificationReadyResponse;
        try {
            verificationReadyResponse = om.readValue(responseJSON, VerificationReadyResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        VerificationInfo info = VerificationInfo.builder()
                .txId(verificationReadyResponse.getSuccess().getTxId())
                .build();
//        if (verificationReadyResponse.getSuccess() != null) {
//            repository.save(info);
//        }



        return VerificationReadyDto.builder()
                .id(info.getId())
                .txid(verificationReadyResponse.getSuccess().getTxId())
                .success(verificationReadyResponse.getSuccess())
                .fail(verificationReadyResponse.getFail())
                .resultType(verificationReadyResponse.getResultType())
                .build();
    }

    private VerificationRequestDto encode(VerificationUserInfo userInfo) {
        TossCertSession session = generator.generate();

        return VerificationRequestDto.builder()
                .userName(session.encrypt(userInfo.getUserName()))
                .userPhone(session.encrypt(userInfo.getUserPhone()))
                .userBirthday(session.encrypt(userInfo.getUserBirthday()))
                .sessionKey(session.getSessionKey())
                .requestType(tossVerificationProperties.getRequestType())
                .triggerType(tossVerificationProperties.getTriggerType())
                .successCallbackUrl(tossVerificationProperties.getSuccessCallbackUrl())
                .failCallbackUrl(tossVerificationProperties.getFailCallbackUrl())
                .build();


    }

    public CheckResponseVo check(VerificationInfo info) {

        TossCertSession session = generator.generate();
        String sessionKey = session.getSessionKey();

//        VerificationInfo info = repository.findById(id).orElseThrow(() -> new ClientException(ErrorCode.ILLEGAL_EX_MESSAGE, "존재하지 않는 결제건"));

        CheckRequestDto dto = CheckRequestDto.builder()
                .txId(info.getTxId())
                .sessionKey(sessionKey)
                .build();

        // 요청
        RestClient restClient = RestClient.builder()
                .baseUrl(tossVerificationProperties.getCheckRequestUrl())
                .build();

        String responseJSON = restClient.post()
                .header(tossVerificationProperties.getTokenKey(),
                        tossVerificationProperties.getTokenType() + tossVerificationProperties.getAccessToken())
                .body(dto)
                .contentType(MediaType.APPLICATION_JSON).retrieve().body(String.class);
        CheckResultDto resultDto;
        try {
            resultDto = om.readValue(responseJSON, CheckResultDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        if (resultDto.getResultType().equalsIgnoreCase("FAIL")) {
            throw new ClientException(ErrorCode.BAD_INFO_EX_MESSAGE, "본인인증에 실패함");
        }


        return decode(session, resultDto);
    }

    private CheckResponseVo decode(TossCertSession session, CheckResultDto dto) {
        return CheckResponseVo.builder()
                .name(session.decrypt(dto.getSuccess().getPersonalData().getName()))
                .gender(session.decrypt(dto.getSuccess().getPersonalData().getGender()))
                .birthday(session.decrypt(dto.getSuccess().getPersonalData().getBirthday()))
                .nationality(session.decrypt(dto.getSuccess().getPersonalData().getNationality()))
                .build();
    }


}
