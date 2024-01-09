package com.team5.projrental.common.aop;

import com.team5.projrental.common.threadpool.MyThreadPool;
import com.team5.projrental.product.ProductMapper;
import com.team5.projrental.product.ProductRepository;
import com.team5.projrental.product.model.CurProductListVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Aspect
@Slf4j
@Component
public class CommonAop {

    private final ProductRepository productRepository;
    private final ExecutorService threadPool;

    public CommonAop(ProductRepository productRepository, MyThreadPool threadPool) {
        this.productRepository = productRepository;
        this.threadPool = threadPool.getThreadPool();
    }

    @AfterReturning(value = "execution(* com.team5.projrental.product.ProductService.getProduct(..))",
            returning = "result")
    public void countView(JoinPoint joinPoint, CurProductListVo result) {

        log.debug("AOP Start");
        threadPool.execute(() -> {
            log.debug("thread name = {}", Thread.currentThread().getName());
            log.debug(productRepository.countView(result.getIproduct()));
        });

}

}
