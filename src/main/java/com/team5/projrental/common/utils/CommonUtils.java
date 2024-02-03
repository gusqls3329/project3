package com.team5.projrental.common.utils;

import com.team5.projrental.common.exception.ErrorCode;
import com.team5.projrental.common.exception.IllegalCategoryException;
import com.team5.projrental.common.exception.IllegalPaymentMethodException;
import com.team5.projrental.product.model.Categories;
import com.vane.badwordfiltering.BadWordFiltering;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

import static com.team5.projrental.common.Const.*;
import static com.team5.projrental.common.exception.ErrorCode.*;

@Component
@Slf4j
public abstract class CommonUtils {

    private static final BadWordFiltering badWordFiltering = new BadWordFiltering();
    public static List<String> filterWord;


    //

    /**
     * 리턴을 위한 status 변환기
     * <p>
     * 문제 istatus : -34(delete_seller_from_canceled), -35(delete_seller_from_canceled,
     * 14(delete_seller_from_canceled), 15(delete_seller_from_canceled)
     *
     * @param beforeStatus
     * @return status for return
     */
    public static int paymentStatusResolver(int beforeStatus) {

        return beforeStatus / 10 == 0 ? beforeStatus : beforeStatus / 10;
    }

    public static boolean ifContainsBadWordTrue(String... words) {
        for (int i = 0; i < words.length; i++) {
            for (String filter : filterWord) {
                if (words[i].contains(filter)) {
                    words[i] = words[i].replace(filter, "");
                }
            }
        }
        log.debug("[CommonUtils.ifContainsBadWordTrue()] words = {}", Arrays.toString(words));
        return Arrays.stream(words).anyMatch(badWordFiltering::blankCheck);
    }

    public static void ifContainsBadWordThrow(Class<? extends RuntimeException> ex, ErrorCode err, String... words) {
        if (ifContainsBadWordTrue(words)) thrown(ex, err);
    }

    public static String ifContainsBadWordChangeThat(String word) {
        return badWordFiltering.change(word);
    }
    //

    public static boolean notBetweenChecker(LocalDate refStartDate, LocalDate refEndDate,
                                            LocalDate newStartDate, LocalDate newEndDate) {

        return newStartDate.isBefore(refStartDate) && newEndDate.isBefore(refStartDate)
                || newStartDate.isAfter(refEndDate) && newEndDate.isAfter(refEndDate);
    }


    /**
     * Integer n 이 null 이거나 0 이면 예외 유발
     *
     * @param ex
     * @param err
     * @param n
     */
    public static void ifObjNullOrZeroThrow(Class<? extends RuntimeException> ex, ErrorCode err, Integer n) {
        if (n == null || n == 0) {
            thrown(ex, err);
        }
    }

    /**
     * boolean b 가 false 면 예외 발생.
     *
     * @param ex
     * @param err
     * @param b
     */
    public static void ifFalseThrow(Class<? extends RuntimeException> ex, ErrorCode err, boolean b) {
        if (!b) thrown(ex, err);
    }

    /**
     * Stream<T> collection(리스트, 맵, 셋 등) 의 .size() 가 int limitNum 에 제공된 숫자보다 크면(초과) 예외 발생
     *
     * @param ex
     * @param err
     * @param collection
     * @param limitNum
     * @param <T>
     */
    public static <T> void checkSizeIfOverLimitNumThrow(Class<? extends RuntimeException> ex, ErrorCode err, Stream<T> collection,
                                                        int limitNum) {
        if (collection.count() > limitNum) {
            thrown(ex, err);
        }
    }

    /**
     * Stream<T> collection(리스트, 맵, 셋 등) 의 .size() 가 int limitNum 에 제공된 숫자보다 작으면(미만) 예외 발생
     *
     * @param ex
     * @param err
     * @param collection
     * @param limitNum
     * @param <T>
     */
    public static <T> void checkSizeIfUnderLimitNumThrow(Class<? extends RuntimeException> ex, ErrorCode err,
                                                         Stream<T> collection,
                                                         int limitNum) {
        if (collection.count() < limitNum) {
            thrown(ex, err);
        }
    }

    public static void ifCategoryNotContainsThrow(List<Categories> icategory) {
        icategory.forEach(CommonUtils::ifCategoryNotContainsThrow);
    }

    /**
     * Integer icategory 가
     *
     * @param icategory
     */
    public static void ifCategoryNotContainsThrow(Categories icategory) {
//        Map<Integer, String> categories = CATEGORIES;
//        return categories.keySet().stream().filter(k -> categories.get(k).equals(category))
//                .findAny().orElseThrow(() -> new IllegalCategoryException(ILLEGAL_CATEGORY_EX_MESSAGE));
//        if (icategory > CATEGORY_COUNT || icategory < 1) thrown(IllegalCategoryException.class, ILLEGAL_CATEGORY_EX_MESSAGE);

        int mainCategory = icategory.getMainCategory();
        int subCategory = icategory.getSubCategory();
        if (subCategory < 0) thrown(IllegalCategoryException.class, ILLEGAL_CATEGORY_EX_MESSAGE);
        if (mainCategory == 1 || mainCategory == 2 || mainCategory == 4) {
            if (subCategory <= 4) {
                return;
            }
        }
        if (mainCategory == 3 || mainCategory == 5) {
            if (subCategory <= 5) {
                return;
            }
        }
        thrown(IllegalCategoryException.class, ILLEGAL_CATEGORY_EX_MESSAGE);


    }

    public static void ifChatUserStatusThrowOrReturn(Integer istatus) {
        if (istatus == DEL_I_STATUS) thrown(IllegalCategoryException.class, ILLEGAL_STATUS_EX_MESSAGE);
    }

    public static Integer ifPaymentMethodNotContainsThrowOrReturn(String paymentMethod) {
        Map<Integer, String> paymentMethods = PAYMENT_METHODS;
        return paymentMethods.keySet().stream().filter(k -> paymentMethods.get(k).equals(paymentMethod))
                .findAny().orElseThrow(() -> new IllegalPaymentMethodException(ILLEGAL_PAYMENT_EX_MESSAGE));
    }

    public static void ifAfterThrow(Class<? extends RuntimeException> ex, ErrorCode err, LocalDate expectedAfter,
                                    LocalDate expectedBefore) {
        if (expectedBefore.isAfter(expectedAfter)) {
            thrown(ex, err);
        }
    }

    public static void ifBeforeThrow(Class<? extends RuntimeException> ex, ErrorCode err, LocalDate expectedAfter,
                                     LocalDate expectedBefore) {
        if (expectedAfter.isBefore(expectedBefore)) {
            thrown(ex, err);
        }
    }

    public static boolean ifAllNullReturnFalse(Object... objs) {
        for (Object obj : objs) {
            if (obj != null)
                return true;
        }
        return false;
    }

//    public static List<String> subEupmyun(String addr) {
//        List<String> result = new ArrayList<>();
//        Arrays.stream(addr.split(" ")).filter(s -> s.contains("읍") || s.contains("동") || s.contains("면"))
//                .forEach(result::add);
//        return result;
//    }

    public static Integer getDepositFromPer(Integer price, Integer percent) {
        return (int) (price * percent * 0.01);
    }

    public static Integer getDepositPerFromPrice(Integer price, Integer deposit) {
        return (price / deposit);
    }

    public static void ifAnyNullThrow(Class<? extends RuntimeException> ex, ErrorCode err, Object... objs) {
        Arrays.stream(objs).forEach(o -> {
            if (o == null) {
                thrown(ex, err);
            }
        });
    }

    public static void ifAnyNotNullThrow(Class<? extends RuntimeException> ex, ErrorCode err, Object... objs) {
        Arrays.stream(objs).forEach(o -> {
            if (o != null) {
                thrown(ex, err);
            }
        });
    }

    public static void ifAllNullThrow(Class<? extends RuntimeException> ex, ErrorCode err, Object... objs) {
        for (Object obj : objs) {
            if (obj != null) {
                return;
            }
        }
        thrown(ex, err);
    }

    public static void ifAllNotNullThrow(Class<? extends RuntimeException> ex, ErrorCode err, Object... objs) {
        for (Object obj : objs) {
            if (obj == null) {
                return;
            }
        }
        thrown(ex, err);
    }

    public static void checkNullOrZeroIfCollectionThrow(Class<? extends RuntimeException> ex, ErrorCode err, Object instance) {
        ifAnyNullThrow(ex, err, instance);
        if (instance instanceof Collection<?>) {
            checkSizeIfUnderLimitNumThrow(ex, err,
                    ((Collection<?>) instance).stream(), 1);
        }
    }

    public static boolean checkNullOrZeroIfCollectionReturnFalse(Class<? extends RuntimeException> ex, ErrorCode err,
                                                                 Object instance) {
        if (!ifAnyNullReturnFalse(ex, err, instance)) return false;
        if (instance instanceof Collection<?>) {
            return checkSizeIfUnderLimitNumReturnFalse(ex, err,
                    ((Collection<?>) instance).stream(), 1);
        }
        return true;
    }

    public static <T> boolean checkSizeIfUnderLimitNumReturnFalse(Class<? extends RuntimeException> ex, ErrorCode err,
                                                                  Stream<T> collection,
                                                                  int limitNum) {
        return collection.count() >= limitNum;
    }

    public static boolean ifAnyNullReturnFalse(Class<? extends RuntimeException> ex, ErrorCode err, Object... objs) {
        for (Object obj : objs) {
            if (obj == null) return false;
        }
        return true;
    }

    //

    /**
     * 예외 throw 메소드 (내부)
     *
     * @param ex
     * @param err
     */
    private static void thrown(Class<? extends RuntimeException> ex,
                               ErrorCode err) {
        try {
            throw ex.getDeclaredConstructor(ErrorCode.class)
                    .newInstance(err);
        } catch (InstantiationException
                 | IllegalAccessException
                 | InvocationTargetException
                 | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

}
