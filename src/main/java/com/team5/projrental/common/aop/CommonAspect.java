package com.team5.projrental.common.aop;

import com.team5.projrental.common.aop.anno.Retry;
import com.team5.projrental.common.exception.RestApiException;
import com.team5.projrental.common.threadpool.MyThreadPoolHolder;
import com.team5.projrental.product.ProductRepository;
import com.team5.projrental.product.model.ProductVo;
import com.team5.projrental.product.model.proc.GetProductViewAopDto;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;

@Aspect
@Slf4j
@Component
public class CommonAspect {

    private final ProductRepository productRepository;
    private final ExecutorService threadPool;

    public CommonAspect(ProductRepository productRepository, MyThreadPoolHolder threadPool) {
        this.productRepository = productRepository;
        this.threadPool = threadPool.getThreadPool();
    }


    /**
     * Product 의 view 를 ++ 하는 AOP (In DB)<br>
     * Custom ThreadPool 사용
     * @param result
     */
    @AfterReturning(value = "@annotation(com.team5.projrental.common.aop.anno.CountView)",
            returning = "result")
    public void countView(ProductVo result) {

        log.debug("AOP Start");
        threadPool.execute(() -> {
            log.debug("thread name = {}", Thread.currentThread().getName());
            String isSucceed = productRepository.countView(new GetProductViewAopDto(result.getIproduct()));
            log.debug(isSucceed);
        });

    }

    /**
     * RuntimeException and extends 발생시 재시도 AOP
     * @param joinPoint
     * @param retry
     * @return Object
     * @throws Throwable
     */
    @Around("@annotation(retry)")
    public Object retry(ProceedingJoinPoint joinPoint, Retry retry) throws Throwable {

        RuntimeException ex = null;

        for (int curTry = 1; curTry <= retry.value(); curTry++) {
            try {
                return joinPoint.proceed();
            } catch (RuntimeException e) {
                log.debug("try count = {} || {}", curTry, retry.value());
                ex = e;
            }
        }
        throw ex;
    }

}
