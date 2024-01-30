package com.team5.projrental.common.scheduler;

import com.team5.projrental.common.aop.anno.Retry;
import com.team5.projrental.payment.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class Scheduler {

    private final PaymentRepository paymentRepository;

    @Scheduled(cron = "1/20 0-3 0 * * *")
    @Retry(5)
    public void updateStatusIfOverRentalEndDate() {
        paymentRepository.updateStatusIfOverRentalEndDate(LocalDate.now());
    }

    @Retry
    @EventListener(ApplicationReadyEvent.class)
    public void postConstruct() {
        updateStatusIfOverRentalEndDate();
    }
}



