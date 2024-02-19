package com.team5.projrental.common.api.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

/**
 * 별도의 인코딩이 필요하지 않고, RestClient 에서 제공해주는 인코딩 방식을 사용할 경우
 * 공공데이터가 아닌 경우 (카카오, 토스 등)
 */
@Component
@RequiredArgsConstructor
public class ApiRequester {

    private final ObjectMapper om;

    // get
    public <T> T get(String baseUrl, String headerKey, String headerValue, String subUri, Class<T> responseEntityType) {
        RestClient restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .build();
        RestClient.RequestHeadersSpec<?> spec = restClient.get()
                .uri(subUri == null ? "" : subUri);
        if (headerKey != null && headerValue != null) {
            spec = spec.header(headerKey, headerValue);
        }
        String response = spec.retrieve().body(String.class);
        try {
            return om.readValue(response, responseEntityType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T get(String baseUrl, String headerKey, String headerValue, Class<T> responseEntityType) {
        return get(baseUrl, headerKey, headerValue, null, responseEntityType);
    }

    public <T> T get(String baseUrl, Class<T> responseEntityType) {
        return get(baseUrl, null, null, null, responseEntityType);
    }

    // post
    public <T, R> T post(String baseUrl, String headerKey, String headerValue, String subUri, R body,
                         Class<T> responseEntityType) {


        /**
         * .uri() 옵션은 String 이 제공되었을경우, 인코딩해서 baseUrl 뒤에 붙인다.
         * 만약 uri 가 제공되면 기존 baseUri 를 제거하고, uri() 에 제공된 URI 객체를 url 자체로 사용한다.
         */

        RestClient restClient = RestClient.create(baseUrl);
        RestClient.RequestHeadersSpec<?> spec = restClient.post()
                .uri(subUri)
                .body(body);
        if (headerKey != null && headerValue != null) {
            spec = spec.header(headerKey, headerValue);
        }
        String response = spec.retrieve().body(String.class);
        try {
            return om.readValue(response, responseEntityType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public <T, R> T post(String baseUrl, R body, Class<T> responseEntityType) {
        return post(baseUrl, null, null, null, body, responseEntityType);
    }

    public <T, R> T post(String baseUrl, String subUri, R body, Class<T> responseEntityType) {
        return post(baseUrl, null, null, subUri, body, responseEntityType);
    }
}
