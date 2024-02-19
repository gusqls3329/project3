package com.team5.projrental.common;

import com.team5.projrental.entities.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class JpaInitializr {

    private final EntityManager em;

    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        User user = new User();
        user.setUid("qwqwqw11");
        user.setUpw("$2a$10$FNCaBrr9DWFu/p9BUbhPjurmo1DExhElugUHR45.YbjW83Qcrekwq");

        em.persist(user);
    }

}
