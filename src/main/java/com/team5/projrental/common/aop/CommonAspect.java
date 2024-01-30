package com.team5.projrental.common.aop;

import com.team5.projrental.common.Const;
import com.team5.projrental.common.aop.anno.CountView;
import com.team5.projrental.common.aop.anno.Retry;
import com.team5.projrental.common.aop.category.CountCategory;
import com.team5.projrental.common.aop.model.DisabledDateInfo;
import com.team5.projrental.common.threadpool.MyThreadPoolHolder;
import com.team5.projrental.payment.model.PaymentInsDto;
import com.team5.projrental.product.RefProductRepository;
import com.team5.projrental.product.RefProductService;
import com.team5.projrental.product.model.ProductVo;
import com.team5.projrental.product.model.proc.GetProductViewAopDto;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;

@Aspect
@Slf4j
@Component
public class CommonAspect {

    private final RefProductService productService;
    private final RefProductRepository productRepository;
    private final ExecutorService threadPool;
    //    public Map<Integer, List<LocalDate>> disabledCache;
    public Map<Integer, DisabledDateInfo> disabledCache;


    public CommonAspect(RefProductService productService, RefProductRepository productRepository, MyThreadPoolHolder threadPool) {
        this.productService = productService;
        this.productRepository = productRepository;
        this.threadPool = threadPool.getThreadPool();
        disabledCache = new ConcurrentHashMap<>();
    }


    /**
     * Product 의 view 를 ++ 하는 AOP (In DB)<br>
     * Custom ThreadPool 사용
     *
     * @param result
     */
    @AfterReturning(value = "@annotation(countView)",
            returning = "result")
    public void countView(Object result, CountView countView) {
        log.debug("AOP Start");
        try {
            countView(result, countView.value());
        } catch (NullPointerException e) {
            log.info("[CommonAspect.countView()]", e);
        }


    }

    // JPA 사용시 Entity 로 메소드 overloading 해서 사용하자 || 지금은 if 문으로 분기했다.
    private void countView(Object result, CountCategory category) {
        if (category == CountCategory.PRODUCT) {
            ProductVo currResult = (ProductVo) result;
            threadPool.execute(() -> {
                log.debug("thread name = {}", Thread.currentThread().getName());
                String isSucceed = productRepository.countView(new GetProductViewAopDto(currResult.getIproduct()));
                log.debug("isSucceed {}", isSucceed);
            });
        }
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

    @AfterReturning("execution(* com.team5.projrental.payment.PaymentService.postPayment(..)) && args(dto)")
    public void deleteCache(JoinPoint joinPoint, PaymentInsDto dto) {
        log.debug("[deleteCache AOP] {}", joinPoint.getSignature());
        disabledCache.remove(dto.getIproduct());
    }

    @Around(value = "execution(* com.team5.projrental.product.ProductController.getDisabledDate(..)) && args(iproduct, y, m)", argNames = "joinPoint,iproduct,y,m")
    public Object returnCacheIfContains(ProceedingJoinPoint joinPoint, Integer iproduct, Integer y, Integer m) {
        DisabledDateInfo inCache = disabledCache.get(iproduct);
        log.debug("[returnCacheIfContains] inCache: {}", inCache);
        try {
            return inCache != null && inCache.getY().equals(y) && inCache.getM().equals(m) ?
                    inCache : joinPoint.proceed();
        } catch (Throwable e) {
            log.debug("[returnCacheIfContains] error: ", e);
            throw new RuntimeException(e);
        }

    }

    @AfterReturning(value = "execution(* com.team5.projrental.product.ProductController.getDisabledDate(..)) && args(iproduct, " +
            "y, m)",
            returning = "disabledDates", argNames = "joinPoint,iproduct,disabledDates,y,m")
    public void addCache(JoinPoint joinPoint, Integer iproduct, List<LocalDate> disabledDates, Integer y, Integer m) {
        log.debug("[addCache AOP] {}", joinPoint.getSignature());
        threadPool.execute(() -> {
            if (disabledCache.size() == Const.DISABLED_CACHE_MAX_NUM) {
                int count = 0;
                for (Integer key : disabledCache.keySet()) {
                    disabledCache.remove(key);
                    if (++count > 9) {
                        break;
                    }
                }
            }
//            disabledCache.put(iproduct, disabledDates);
            disabledCache.put(iproduct, new DisabledDateInfo(disabledDates, y, m));

            disabledCache.keySet().forEach(k -> log.debug("[addCache AOP] total Cache = key: {}, values: {}", k,
                    disabledCache.get(k)));

            log.debug("[addCache AOP] memory check {}/{} free: {}",
                    Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory(),
                    Runtime.getRuntime().totalMemory(),
                    Runtime.getRuntime().freeMemory()
            );
        });
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initCacheData() {
        int limitCount = Const.DISABLED_CACHE_MAX_NUM;
        LocalDate now = LocalDate.now();
        List<Integer> allIproductsLimit = productRepository.getAllIproductsLimit(limitCount);

        allIproductsLimit.forEach(i -> {
            List<LocalDate> disabledDate = productService.getDisabledDate(i, now.getYear(), now.getMonthValue());
            if (disabledDate != null && !disabledDate.isEmpty()) {
                this.disabledCache.put(i, new DisabledDateInfo(disabledDate, now.getYear(), now.getMonthValue()));
            }
        });

        log.debug("Cache init -> disabledCache {}", this.disabledCache);
    }

}
