package com.team5.projrental.common.aop;

import com.team5.projrental.product.ProductMapper;
import com.team5.projrental.product.ProductRepository;
import com.team5.projrental.product.model.CurProductListVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
@RequiredArgsConstructor
public class CommonAop {

    private final ProductRepository productRepository;

    @AfterReturning(value = "execution(* com.team5.projrental.product.ProductService.getProduct(..))",
            returning = "result")
    public void countView(JoinPoint joinPoint, CurProductListVo result) {
        log.debug("AOP Start");
        productRepository.countView(result.getIproduct());
    }

}
