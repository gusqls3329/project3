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

        /* TODO: 1/10/24
            Thread 추가 - 100개로 별도의 스레드풀 만들어서 빈등록, DI 받아 사용하자. - 100은 Const 로 만들자. 차후 변경 유리하게.
            --by Hyunmin */

        log.debug("AOP Start");
        log.debug(productRepository.countView(result.getIproduct()));
    }

}
