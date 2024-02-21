package com.team5.projrental.websocket;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitConsumer {

    @RabbitListener(queues = "PRE_NEWS")
    public void receiveMessage(final String message) {
        System.out.println(message);
    }
}
