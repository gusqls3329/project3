package com.team5.projrental.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team5.projrental.common.exception.IllegalCategoryException;
import com.team5.projrental.common.exception.IllegalPaymentMethodException;
import com.team5.projrental.common.exception.RestApiException;
import com.team5.projrental.common.model.restapi.Addrs;
import com.team5.projrental.common.model.restapi.Documents;
import com.team5.projrental.product.model.innermodel.StoredFileInfo;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

import static com.team5.projrental.common.Const.*;
import static com.team5.projrental.common.Const.ILLEGAL_CATEGORY_EX_MESSAGE;

@Component
public class CommonUtils {

    public static void ifFalseThrow(Class<? extends RuntimeException> ex, String message, boolean b) {
        if (!b) thrown(ex, message);
    }

    public static <T> void checkSizeIfOverLimitNumThrow(Class<? extends RuntimeException> ex, String message, Stream<T> collection,
                                                        int limitNum) {
        if (collection.count() > limitNum) {
            thrown(ex, message);
        }
    }

    public static <T> void checkSizeIfUnderLimitNumThrow(Class<? extends RuntimeException> ex, String message,
                                                         Stream<T> collection,
                                                         int limitNum) {
        if (collection.count() < limitNum) {
            thrown(ex, message);
        }
    }

    public static Integer ifCategoryNotContainsThrowOrReturn(String category) {
        Map<Integer, String> categories = CATEGORIES;
        return categories.keySet().stream().filter(k -> categories.get(k).equals(category))
                .findAny().orElseThrow(() -> new IllegalCategoryException(ILLEGAL_CATEGORY_EX_MESSAGE));
    }
    public static Integer ifPaymentMethodNotContainsThrowOrReturn(String paymentMethod) {
        Map<Integer, String> paymentMethods = PAYMENT_METHODS;
        return paymentMethods.keySet().stream().filter(k -> paymentMethods.get(k).equals(paymentMethod))
                .findAny().orElseThrow(() -> new IllegalPaymentMethodException(ILLEGAL_PAYMENT_EX_MESSAGE));
    }

    public static void ifAfterThrow(Class<? extends RuntimeException> ex, String message, LocalDate expectedAfter,
                                    LocalDate expectedBefore) {
        if (expectedBefore.isAfter(expectedAfter)) {
            thrown(ex, message);
        }
    }

    public static void ifBeforeThrow(Class<? extends RuntimeException> ex, String message, LocalDate expectedAfter,
                                     LocalDate expectedBefore) {
        if (expectedAfter.isBefore(expectedBefore)) {
            thrown(ex, message);
        }
    }



    /**
     * default 접근 제한자 사용, 동일 패키지의 AxisProvider 에서만 사용함.<br>
     * ㄴ> 해당 유틸을 직접 사용하는것을 막기 위한 제약.<br>
     * 외부에서 해당 기능이 필요할 경우 AxisProvider 를 DI 받아 사용해야 함.
     * @param fullAddr
     * @return Map<String, Double>
     */
    static Map<String, Double> getAxis(String fullAddr) {
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
            throw new RestApiException(SERVER_ERR_MESSAGE);
        }
        Addrs addrs = documents.getDocuments().stream().filter(Objects::nonNull)
                .findFirst().orElseThrow(() -> new RestApiException(SERVER_ERR_MESSAGE));
        axisMap.put(AXIS_X, Double.parseDouble(addrs.getX()));
        axisMap.put(AXIS_Y, Double.parseDouble(addrs.getY()));
        if (addrs.getX().isEmpty() || addrs.getY().isEmpty()) {
            throw new RestApiException(SERVER_ERR_MESSAGE);
        }
        return axisMap;
    }

    public static List<String> subEupmyun(String fullAddr) {
        List<String> result = new ArrayList<>();
        Arrays.stream(fullAddr.split(" ")).filter(s -> s.contains("읍") || s.contains("동") || s.contains("면"))
                .forEach(result::add);
        return result;
    }

    public static Integer getDepositFromPer(Integer price, Integer percent) {
        return (int) (price * percent * 0.01);
    }


    public static void ifAnyNullThrow(Class<? extends RuntimeException> ex, String message, Object... objs) {
        Arrays.stream(objs).forEach(o -> {
            if (o == null) {
                thrown(ex, message);
            }
        });
    }

    public static void ifAnyNotNullThrow(Class<? extends RuntimeException> ex, String message, Object... objs) {
        Arrays.stream(objs).forEach(o -> {
            if (o != null) {
                thrown(ex, message);
            }
        });
    }

    public static void ifAllNullThrow(Class<? extends RuntimeException> ex, String message, Object... objs) {
        for (Object obj : objs) {
            if (obj != null) {
                return;
            }
        }
        thrown(ex, message);
    }

    public static void ifAllNotNullThrow(Class<? extends RuntimeException> ex, String message, Object... objs) {
        for (Object obj : objs) {
            if (obj == null) {
                return;
            }
        }
        thrown(ex, message);
    }

    public static void checkNullOrZeroIfCollectionThrow(Class<? extends RuntimeException> ex, String message, Object instance) {
        ifAnyNullThrow(ex, message, instance);
        if (instance instanceof Collection<?>) {
        checkSizeIfUnderLimitNumThrow(ex, message,
                    ((Collection<?>) instance).stream(), 1);
        }
    }


    // 파일 업로드 배운 후 완성시킬 예정.

    /**
     * 하나의 사진 파일 조회
     *
     * @param pic
     * @return Resource
     */
    public static Resource getPic(StoredFileInfo pic) {
        /* TODO: 1/9/24
            차후 파일 업로드 배우면 수정.
            --by Hyunmin */
        return new FileSystemResource("test");
    }

    // 파일 업로드 배운 후 완성시킬 예정.

    /**
     * 2개 이상의 사진 파일 조회
     * [getPic 내부 호출]
     *
     * @param pic
     * @return List<Resource>
     */
    public static List<Resource> getPic(List<StoredFileInfo> pic) {
        List<Resource> results = new ArrayList<>();
        pic.forEach(p -> results.add(getPic(p)));
        return results;
    }

    // 파일 업로드 배운 후 완성시킬 예정.

    /**
     * 하나의 사진 파일 저장
     *
     * @param multipartFile
     * @return StoredFileInfo
     */
    public static StoredFileInfo savePic(MultipartFile multipartFile) {

        /* TODO: 1/9/24
            차후 파일 업로드 배우면 수정.
            --by Hyunmin */
        // tmp value
        return new StoredFileInfo("tmp", "tmp");
    }

    // 파일 업로드 배운 후 완성시킬 예정.

    /**
     * 2개 이상의 사진 파일 저장
     * [savePic 내부 호출]
     *
     * @param multipartFiles
     * @return List<StoredFileInfo>
     */
    public static List<StoredFileInfo> savePic(List<MultipartFile> multipartFiles) {
        List<StoredFileInfo> result = new ArrayList<>();
        multipartFiles.forEach(file -> {
            result.add(savePic(file));
        });
        return result;
    }

    //

    /**
     * 예외 throw 메소드 (내부)
     * @param ex
     * @param message
     */
    private static void thrown(Class<? extends RuntimeException> ex, String message) {
        try {
            throw ex.getDeclaredConstructor(String.class).newInstance(message);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

}
