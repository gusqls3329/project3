package com.team5.projrental.common.aop;

import com.team5.projrental.common.aop.anno.Retry;
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

import java.time.LocalDate;
import java.util.concurrent.ExecutorService;

@Aspect
@Slf4j
@Component
public class CommonAspect {

    private final ProductRepository productRepository;
    private final ExecutorService threadPool;
//    public Map<Integer, List<LocalDate>> disabledCache;


    public CommonAspect(ProductRepository productRepository, MyThreadPoolHolder threadPool) {
        this.productRepository = productRepository;
        this.threadPool = threadPool.getThreadPool();
//        disabledCache = new ConcurrentHashMap<>();
    }


    /**
     * Product 의 view 를 ++ 하는 AOP (In DB)<br>
     * Custom ThreadPool 사용
     *
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
     *
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

    @Around("execution(* com.team5.projrental.payment.PaymentRepository.updateStatusIfOverRentalEndDate(..)) && args(now)")
    public int doLog(ProceedingJoinPoint joinPoint, LocalDate now) throws Throwable {

        int changedColumn = (int) joinPoint.proceed();
        log.debug("[Scheduler] [updateStatusIfOverRentalEndDate] now = {} changedColumn = {}",
                now, changedColumn);
        return changedColumn;
    }


    //    @Before("execution(* com.team5.projrental.payment.PaymentService.postPayment(..)) && args(dto)")
//    public void deleteCache(JoinPoint joinPoint, PaymentInsDto dto) {
//        log.debug("[deleteCache AOP] {}", joinPoint.getSignature());
//        disabledCache.remove(dto.getIproduct());
//    }

//    @AfterReturning("execution(* com.team5.projrental.payment.PaymentService.postPayment(..)) && args(dto)")
//    public void deleteCache(JoinPoint joinPoint, PaymentInsDto dto) {
//        log.debug("[deleteCache AOP] {}", joinPoint.getSignature());
//        disabledCache.remove(dto.getIproduct());
//    }

//    @Around("execution(* com.team5.projrental.product.ProductController.getDisabledDate(..)) && args(iproduct, ..)")
//    public Object returnCacheIfContains(ProceedingJoinPoint joinPoint, Integer iproduct) {
//        List<LocalDate> inCache = disabledCache.get(iproduct);
//        log.debug("[returnCacheIfContains] inCache: {}", inCache);
//        try {
//            return inCache != null ? inCache : joinPoint.proceed();
//        } catch (Throwable e) {
//            log.debug("[returnCacheIfContains] error: ", e);
//            throw new RuntimeException(e);
//        }
//
//    }

//    @AfterReturning(value = "execution(* com.team5.projrental.product.ProductController.getDisabledDate(..)) && args(iproduct, " +
//            "..)",
//            returning = "disabledDates", argNames = "joinPoint,iproduct,disabledDates")
//    public void addCache(JoinPoint joinPoint, Integer iproduct, List<LocalDate> disabledDates) {
//        log.debug("[addCache AOP] {}", joinPoint.getSignature());
//        threadPool.execute(() -> {
//            if (disabledCache.size() == Const.DISABLED_CACHE_MAX_NUM) {
//                int count = 0;
//                for (Integer key : disabledCache.keySet()) {
//                    disabledCache.remove(key);
//                    if (++count > 9) {
//                        break;
//                    }
//                }
//            }
//            disabledCache.put(iproduct, disabledDates);
//
//            disabledCache.keySet().forEach(k -> log.debug("[addCache AOP] total Cache = key: {}, values: {}", k,
//                    disabledCache.get(k)));
//
//            log.debug("[addCache AOP] memory check {}/{} free: {}",
//                    Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory(),
//                    Runtime.getRuntime().totalMemory(),
//                    Runtime.getRuntime().freeMemory()
//            );
//        });
//    }


}
