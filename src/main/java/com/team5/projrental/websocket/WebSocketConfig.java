package com.team5.projrental.websocket;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override // MessageBroker를 설정하는 내용
    public void configureMessageBroker(MessageBrokerRegistry regostry) {

        //regostry.setApplicationDestinationPrefixes("/test");
        regostry.enableSimpleBroker("/queue", "/topic");
        // enableSimpleBroker는 스프링에서 내장브로커를 사용하겠다는 선언
        // "/queue", "/topic"는 해당값이 Prefixes로 붙은 메세지가 송신 되었을 때 그메세지를 메세지 브로커가 처리하겠다는 의미
        // "/queue", "/topic"가 붙은 경로로 메세지가 송신 되었을 때 심플 브로커가 그 메세지를 받고 구독자들에게 전달
        // 일종의 컨벤션으로 queue는 메세지가 1:1로 송신될 때, topic은 1:다수 브로드캐스팅될 때 주로 사용한다고 함.
        regostry.setApplicationDestinationPrefixes("/app");
        // 메세지 처리나 가공이 필요한 경우 handler로 전달해서 가공할 수 있도록 함
    }

    @Override // 처음 웹소켓 헨드셰이크를 위한 주소, 여기서는 핸들러를 별도로 지정하지 않아도 된다. (컨트롤러 방식으로 간편하게 사용하기떄문)
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        registry.addEndpoint("/gs-guide-websocket").withSockJS();
    }


}