package com.team5.projrental.user.verification.comp;

import com.team5.projrental.common.api.client.PubDataApiRequester;
import com.team5.projrental.user.verification.comp.model.*;
import com.team5.projrental.user.verification.comp.properties.CompCodeProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CompCodeValidator {

    private final PubDataApiRequester apiRequester;
    private final CompCodeProperties compCodeProperties;

    public CompCodeVo validate(CompCodeDto dto) {

        Businesses businesses = Businesses.builder()
                .b_no(dto.getCompCode().replace("-", ""))
                .start_dt(dto.getStartAt().format(DateTimeFormatter.ofPattern("YYYYMMdd")))
                .p_nm(dto.getCompCEO())
                .b_nm(dto.getCompNm() == null ? "" : dto.getCompNm())
                .build();
        CompCodeRequestDto requestDto = CompCodeRequestDto.builder()
                .businesses(List.of(businesses))
                .build();
        String subUri = "?" + compCodeProperties.getServiceCodeKey() + "=" + compCodeProperties.getServiceCodeValue();
        CompCodeResponseDto result = apiRequester.post(compCodeProperties.getBaseUrl(), subUri, requestDto, CompCodeResponseDto.class);
        log.info("result = {}", result);
        try {
            return CompCodeVo.builder()
                    .statusCode(result.getStatus_code())
                    .compCode(result.getData().get(0).getStatus().getB_no())
                    .compNm(result.getData().get(0).getRequest_param().getB_nm())
                    .compCEO(result.getData().get(0).getRequest_param().getP_nm())
                    .validMsg(result.getData().get(0).getValid_msg())
                    .build();
        } catch (NullPointerException e) {
            return CompCodeVo.builder()
                    .statusCode("FAIL")
                    .validMsg(result.getData().get(0).getValid_msg())
                    .build();
        }
    }

}
