package com.team5.projrental.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Aspect
@Component
@Slf4j
@Profile({"default", "hyunmin"})
public class LocalLoggerAspect {
    private ThreadLocal<Long> startTime;
    private ThreadLocal<Long> endTime;

    public LocalLoggerAspect() {
        this.startTime = new ThreadLocal<>();
        this.endTime = new ThreadLocal<>();
    }

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


        log.debug("\n\nCALL\n\t{} \n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tㄴ> {} \nARGS \n\t{}\nON\n\t{}\n",
                joinPoint.getSignature(),
                joinPoint.getTarget(),
                joinPoint.getArgs(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
        this.startTime.set(System.currentTimeMillis());
    }

    @AfterReturning(value = "controller() || service() || repository() || mapper()",
            returning = "returnVal")
    public void afterReturn(JoinPoint joinPoint, Object returnVal) {
        boolean flag = false;
        if (joinPoint.getSignature().getDeclaringTypeName().contains("ontroller")) {
            this.endTime.set(System.currentTimeMillis());
            flag = true;
        }
        log.debug("\nRETURN \n\t{}\nRETURN VAL \n\t{}\n", joinPoint.getSignature(), returnVal);
        if (flag) {
            log.debug("\nDURATION \n\t{}ms\n\n----------- END -----------", this.endTime.get() - this.startTime.get());
            this.endTime.remove();
            this.startTime.remove();
        }


    }


}
