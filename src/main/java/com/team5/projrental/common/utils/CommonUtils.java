package com.team5.projrental.common.utils;

import com.team5.projrental.common.exception.IllegalCategoryException;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.Stream;

import static com.team5.projrental.common.Const.*;
import static com.team5.projrental.common.Const.ILLEGAL_CATEGORY_EX_MESSAGE;

@Component
public class CommonUtils {

    /**
     * 하나라도 null 일 경우 , ex 에 해당하는 예외를 message 를 담아서, throw 함.
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

    public void ifCategoryNotContainsThrow(String category) {
        if (!CATEGORIES.contains(category)) {
            thrown(IllegalCategoryException.class, ILLEGAL_CATEGORY_EX_MESSAGE);

        }
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

}
