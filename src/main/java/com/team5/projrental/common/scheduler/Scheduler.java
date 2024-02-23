package com.team5.projrental.common.scheduler;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.team5.projrental.common.aop.anno.Retry;
import com.team5.projrental.entities.PaymentInfo;
import com.team5.projrental.entities.QPaymentInfo;
import com.team5.projrental.entities.enums.PaymentInfoStatus;
import com.team5.projrental.payment.PaymentMybatisRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class Scheduler {

    private final PaymentMybatisRepository paymentMybatisRepository;
    private final EntityManager em;
    private final JPAQueryFactory query;

    @Scheduled(cron = "1/20 0-3 0 * * *")
    @Retry(5)
    public void updateStatusIfOverRentalEndDate() {
        /*
                update t_payment_status PS join t_payment PA on PS.ipayment = PA.ipayment set PS.istatus = -4
                where PA.rental_end_date <![CDATA[<=]]> #{now} and PS.istatus = 0
         */
        QPaymentInfo paymentInfo = QPaymentInfo.paymentInfo;
        List<PaymentInfo> findPaymentInfo = query.select(paymentInfo)
                .from(paymentInfo)
                .where(paymentInfo.payment.createdAt.loe(LocalDateTime.now()).and(paymentInfo.status.eq(PaymentInfoStatus.ACTIVATED)))
                .fetch();

        findPaymentInfo.forEach(i -> i.setStatus(PaymentInfoStatus.EXPIRED));

//        paymentRepository.updateStatusIfOverRentalEndDate(LocalDate.now());
    }

    @Retry
    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void postConstruct() {
        updateStatusIfOverRentalEndDate();
    }
}



