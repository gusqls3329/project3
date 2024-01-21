package com.team5.projrental.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@Slf4j
@Profile({"local", "hyunmin"})
public class LocalLoggerAspect {
    private long startTime;
    private long endTime;

    @Pointcut("execution(* com.team5.projrental..*Controller*.*(..))")
    public void controller() {
    }

    @Pointcut("execution(* com.team5.projrental..*Service*.*(..))")
    public void service() {
    }

    @Pointcut("execution(* com.team5.projrental..*Repository*.*(..))")
    public void repository() {
    }

    @Pointcut("execution(* com.team5.projrental..*Mapper*.*(..))")
    public void mapper() {
    }

    @Before("controller() || service() || repository() || mapper()")
    public void beforeAll(JoinPoint joinPoint) {


        log.info("\n\nCALL\n\t{} \n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tㄴ> {} \nARGS \n\t{}\nON\n\t{}\n",
                joinPoint.getSignature(),
                joinPoint.getTarget(),
                joinPoint.getArgs(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
        this.startTime = System.currentTimeMillis();
    }

    @AfterReturning(value = "controller() || service() || repository() || mapper()",
            returning = "returnVal")
    public void afterReturn(JoinPoint joinPoint, Object returnVal) {
        boolean flag = false;
        if (joinPoint.getSignature().getDeclaringTypeName().contains("ontroller")) {
            this.endTime = System.currentTimeMillis();
            flag = true;
        }
        Map<String, String> result = new HashMap<>();
        Class<?> clazz = returnVal.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        Method[] declaredMethods = clazz.getDeclaredMethods();
        Arrays.stream(declaredFields).forEach(f -> Arrays.stream(declaredMethods).forEach(m -> {
            if (m.getName().toLowerCase().contains("get") && m.getName().toLowerCase().contains(f.getName())) {
                try {
                    Object invoke = m.invoke(returnVal);
                    if(invoke == null) return;
                    result.put(f.getName(), String.valueOf(invoke));
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }));
        log.info("\nRETURN \n\t{}\nRETURN VAL \n\t{}\n", joinPoint.getSignature(), result.isEmpty() ? "empty" : result);
        if (flag) {
            log.info("\nDURATION \n\t{}ms", this.endTime - this.startTime);
        }
    }


}
