package com.team5.projrental.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
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
@Profile({"local", "hyunmin"})
public class LocalLoggerAspect {

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


        log.info("\n\nCALL\n{} \n\t-> {} \n\t\targs = {}\nON\n{}\n",
                joinPoint.getSignature(),
                joinPoint.getTarget(),
                joinPoint.getArgs(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
    }
}
