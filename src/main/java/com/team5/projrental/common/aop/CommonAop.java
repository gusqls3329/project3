package com.team5.projrental.common.aop;

import com.team5.projrental.common.threadpool.MyThreadPoolHolder;
import com.team5.projrental.product.ProductRepository;
import com.team5.projrental.product.model.ProductVo;
import com.team5.projrental.product.model.proc.GetProductViewAopDto;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;

@Aspect
@Slf4j
@Component
public class CommonAop {

    private final ProductRepository productRepository;
    private final ExecutorService threadPool;

    public CommonAop(ProductRepository productRepository, MyThreadPoolHolder threadPool) {
        this.productRepository = productRepository;
        this.threadPool = threadPool.getThreadPool();
    }

    @AfterReturning(value = "execution(* com.team5.projrental.product.ProductService.getProduct(..))",
            returning = "result")
    public void countView(JoinPoint joinPoint, ProductVo result) {

        log.debug("AOP Start");
        threadPool.execute(() -> {
            log.debug("thread name = {}", Thread.currentThread().getName());
            log.debug(productRepository.countView(new GetProductViewAopDto(result.getIproduct())));
        });

    }

}
