package com.team5.projrental.common.aop;

import com.team5.projrental.common.sse.SseEmitterRepository;
import com.team5.projrental.common.sse.model.FindDiffUserDto;
import com.team5.projrental.common.sse.model.RejectMessageInfo;
import com.team5.projrental.common.sse.responseproperties.Properties;
import com.team5.projrental.common.threadpool.MyThreadPoolHolder;
import com.team5.projrental.payment.review.model.RivewDto;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;

@Slf4j
@Component
@Aspect
public class SseEmitterAspect {
    private final ApplicationEventPublisher applicationEventPublisher;
    private final SseEmitterRepository sseEmitterRepository;
    private ExecutorService threadPool;

    @Autowired
    public SseEmitterAspect(ApplicationEventPublisher applicationEventPublisher, SseEmitterRepository sseEmitterRepository,
                            MyThreadPoolHolder myThreadPoolHolder) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.sseEmitterRepository = sseEmitterRepository;
        this.threadPool = myThreadPoolHolder.getThreadPool();
    }

    @Pointcut("execution(* com.team5.projrental.payment.review.PaymentReviewService.*())")
    public void postReview() {

    }


    // 리뷰 작성시점에 상대유저에게 푸시 보내기 위한 이벤트 발생
    // 만약 로그인유저가 리뷰를 썼는데, 상대 유저가 리뷰를 썼다면 거래완료 메시지와 코드를 푸시
    @AfterReturning("postReview() && args(dto)")
    public void makeEvent(JoinPoint joinPoint, RivewDto dto) {
        threadPool.execute(() -> {
            FindDiffUserDto findDiffUserDto = sseEmitterRepository.findDiffUserBy(dto.getIpayment(), dto.getIuser());
            log.debug("[SseEmitterAspect.makeEvent] {}", joinPoint.getSignature());

            Properties properties = findDiffUserDto.getReviewCount() < 2 ?
                    Properties.DIFF_USER_WRITE_REVIEW :
                    Properties.PAYMENT_IS_FINISHED;

            applicationEventPublisher.publishEvent(catchEventPropertiesGenerator(findDiffUserDto.getDiffIuser(),
                    properties.getMessage().get(), properties.getCode().get(), null, "review"));
        });
    }


    //
    private RejectMessageInfo catchEventPropertiesGenerator(Integer iuser, String message,
                                                            Integer code, Integer num,
                                                            String name) {
        return new RejectMessageInfo(iuser, message, code, num, name);

    }

}
