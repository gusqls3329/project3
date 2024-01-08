package com.team5.projrental.common.utils;

import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

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
                try {
                    throw ex.getDeclaredConstructor(String.class).newInstance(message);
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

}
