package com.team5.projrental.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team5.projrental.common.aop.anno.Retry;
import com.team5.projrental.common.exception.BadAddressInfoException;
import com.team5.projrental.common.exception.RestApiException;
import com.team5.projrental.common.model.restapi.Addrs;
import com.team5.projrental.common.model.restapi.Documents;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.team5.projrental.common.exception.ErrorCode.*;

@Component
@Slf4j
public class KakaoAxisGenerator implements AxisGenerator {


    private final ObjectMapper objectMapper;
    private String url;
    private String headerKey;
    private String headerValue;

    public KakaoAxisGenerator(ObjectMapper objectMapper,
                              @Value("${api.axis.kakao.url}")
                              String url,
                              @Value("${api.axis.kakao.header-key}")
                              String headerKey,
                              @Value("${api.axis.kakao.header-value}")
                              String headerValue) {
        this.objectMapper = objectMapper;
        this.url = url;
        this.headerKey = headerKey;
        this.headerValue = headerValue;

    }

    /**
     * 해당 기능이 필요할 경우 AxisGenerator 를 DI 받아 사용해야 함.
     * ㄴ> @Retry 어노테이션을 적용하기 위함.
     *
     * @param addr
     * @return Map<String, Double>
     */
    @Retry(5)
    public Addrs getAxis(String addr) {
        addr = addr.contains("%") ? addr.replaceAll("%", " ") :
                addr.contains("-") ? addr.replaceAll("-", " ") : addr;

        log.debug("addr = {}", addr);
        StringBuilder sb = new StringBuilder();
        sb.append("?query=").append(addr);
        String query = sb.toString();


        RestClient restClient = RestClient.builder()
                .baseUrl(url)
                .build();

        String result = restClient.get()
                .uri(query)
                .header(headerKey, headerValue)
                .retrieve()
                .body(String.class);

        log.debug("result = {}", result);

        Documents documents;

        try {
            documents = objectMapper.readValue(result, Documents.class);
        } catch (JsonProcessingException e) {
            throw new RestApiException(SERVER_ERR_MESSAGE);
        }
        Addrs addrs = documents.getDocuments().stream().filter(Objects::nonNull)
                .findFirst().orElseThrow(() -> new BadAddressInfoException(BAD_ADDRESS_INFO_EX_MESSAGE));
        if (addrs.getX().isEmpty() || addrs.getY().isEmpty()) {
            throw new RestApiException(SERVER_ERR_MESSAGE);
        }

        return addrs;
    }

}
