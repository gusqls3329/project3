package com.team5.projrental.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team5.projrental.common.Const;
import com.team5.projrental.common.exception.IllegalCategoryException;
import com.team5.projrental.common.model.restapi.Addrs;
import com.team5.projrental.common.model.restapi.Documents;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

import static com.team5.projrental.common.Const.*;
import static com.team5.projrental.common.Const.ILLEGAL_CATEGORY_EX_MESSAGE;

@Component
public class CommonUtils {

    /**
     * 하나라도 null 일 경우 , ex 에 해당하는 예외를 message 를 담아서, throw 함.
     *
     * @param ex
     * @param message
     * @param objs
     */
    public void anyNullThrown(Class<? extends RuntimeException> ex, String message, Object... objs) {
        Arrays.stream(objs).forEach(o -> {
            if (o == null) {
                thrown(ex, message);
            }
        });
    }

    public <T> void checkSizeIfOverLimitNumThrow(Class<? extends RuntimeException> ex, String message, Stream<T> collection,
                                                 int limitNum) {
        if (collection.count() > limitNum) {
            thrown(ex, message);
        }
    }

    public Integer ifCategoryNotContainsThrowOrReturn(String category) {
        Map<Integer, String> categories = CATEGORIES;
        return categories.keySet().stream().filter(k -> categories.get(k).equals(category))
                .findAny().orElseThrow(() -> new IllegalCategoryException(ILLEGAL_CATEGORY_EX_MESSAGE));
    }

    public void ifAfterThrow(Class<? extends RuntimeException> ex, String message, LocalDate expectedAfter,
                             LocalDate expectedBefore) {
        if (expectedBefore.isAfter(expectedAfter)) {
            thrown(ex, message);
        }
    }

    public void ifBeforeThrow(Class<? extends RuntimeException> ex, String message, LocalDate expectedAfter,
                              LocalDate expectedBefore) {
        if (expectedAfter.isBefore(expectedBefore)) {
            thrown(ex, message);
        }
    }

    private void thrown(Class<? extends RuntimeException> ex, String message) {
        try {
            throw ex.getDeclaredConstructor(String.class).newInstance(message);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, Double> getAxis(String fullAddr) {
        Map<String, Double> axisMap = new HashMap<>();

        StringBuilder sb = new StringBuilder();
        sb.append("?query=").append(fullAddr);
        String query = sb.toString();
        String url = "https://dapi.kakao.com/v2/local/search/address";
        String headerKey = "Authorization";
        String headerValue = "KakaoAK 7b5a7755251df2d95b48052980a5c025";

        RestClient restClient = RestClient.builder()
                .baseUrl(url)
                .build();

        String result = restClient.get()
                .uri(query)
                .header(headerKey, headerValue)
                .retrieve()
                .body(String.class);

        Documents documents;
        ObjectMapper om = new ObjectMapper();
        try {
            documents = om.readValue(result, Documents.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(Const.SERVER_ERR_MESSAGE);
        }
        Addrs addrs = documents.getDocuments().stream().filter(Objects::nonNull)
                .findFirst().orElseThrow(() -> new RuntimeException(SERVER_ERR_MESSAGE));
        axisMap.put("x", Double.parseDouble(addrs.getX()));
        axisMap.put("y", Double.parseDouble(addrs.getY()));

        return axisMap;
    }

    public List<String> subEupmyun(String fullAddr) {
        List<String> result = new ArrayList<>();
        Arrays.stream(fullAddr.split(" ")).filter(s -> s.contains("읍") || s.contains("동") || s.contains("면"))
                .forEach(result::add);
        return result;
    }
}
