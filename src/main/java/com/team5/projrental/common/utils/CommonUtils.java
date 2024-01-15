package com.team5.projrental.common.utils;

import com.team5.projrental.common.exception.IllegalCategoryException;
import com.team5.projrental.common.exception.IllegalPaymentMethodException;
import com.team5.projrental.product.model.innermodel.StoredFileInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.InvocationTargetException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

import static com.team5.projrental.common.Const.*;

@Component
@Slf4j
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

    public static void ifCategoryNotContainsThrowOrReturn(Integer icategory) {
//        Map<Integer, String> categories = CATEGORIES;
//        return categories.keySet().stream().filter(k -> categories.get(k).equals(category))
//                .findAny().orElseThrow(() -> new IllegalCategoryException(ILLEGAL_CATEGORY_EX_MESSAGE));
        boolean contains = CATEGORIES.keySet().contains(icategory);
        if (contains) return;
        thrown(IllegalCategoryException.class, ILLEGAL_CATEGORY_EX_MESSAGE);
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


    public static List<String> subEupmyun(String addr) {
        List<String> result = new ArrayList<>();
        Arrays.stream(addr.split(" ")).filter(s -> s.contains("읍") || s.contains("동") || s.contains("면"))
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


    //

    /**
     * 예외 throw 메소드 (내부)
     *
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
