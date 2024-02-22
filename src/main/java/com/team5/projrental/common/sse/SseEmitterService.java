package com.team5.projrental.common.sse;

import com.team5.projrental.common.security.AuthenticationFacade;
import com.team5.projrental.common.sse.holder.SseEmitterHolder;
import com.team5.projrental.common.sse.model.SseMessageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
@RequiredArgsConstructor
public class SseEmitterService {
    private final SseEmitterHolder emitterHolder;
    private final AuthenticationFacade authenticationFacade;


    public ResponseEntity<SseEmitter> connect() {
        return ResponseEntity.ok(emitterHolder.add(authenticationFacade.getLoginUserPk()));
    }

    @Async
    @EventListener
    public void catchEvent(SseMessageInfo info) {
        emitterHolder.send(info);
    }

}
