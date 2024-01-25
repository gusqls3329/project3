package com.team5.projrental.common.sse.holder;

import com.team5.projrental.common.sse.SseEmitterRepository;
import com.team5.projrental.common.sse.model.SseResponse;
import com.team5.projrental.common.sse.responseproperties.Properties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class SseEmitterHolder {
    private Map<Integer, SseEmitter> emitterMap;

    private SseEmitterRepository emitterRepository;

    public SseEmitterHolder(SseEmitterRepository emitterRepository) {
        this.emitterRepository = emitterRepository;
        this.emitterMap = new ConcurrentHashMap<>();
    }

    /**
     * code 에 구분자로 필요한 성질을 동적으로 추가함.
     *
     * @param iuser
     * @return SseEmitter
     */
    public SseEmitter add(Integer iuser) {
        emitterMap.remove(iuser);

        SseEmitter sseEmitter = new SseEmitter();
        emitterMap.put(iuser, sseEmitter);

        if (!send(iuser, "create new emitter", Properties.SEND_EXPIRED_PAYMENT_COUNT,
                emitterRepository.findExpiredPaymentCountBy(iuser))) {
            return null;
        }

        return sseEmitter;
    }

    public boolean send(Integer iuser, Properties pushInfo) {
        return send(iuser, "sse push", pushInfo);
    }

    public boolean send(Integer iuser, String sseEmitterName, Properties pushInfo) {
        return send(iuser, sseEmitterName, pushInfo, null);
    }

    public boolean send(Integer iuser, String sseEmitterName, Properties pushInfo, Integer addedCode) {

        SseResponse sseResponse = SseResponse.builder()
                .iuser(iuser)
                .code(pushInfo.getCode().get() + (addedCode == null ? "" : String.valueOf(addedCode)))
                .message(pushInfo.getMessage().get())
                .build();
        try {
            emitterMap.get(iuser).send(SseEmitter.event()
                    .name(sseEmitterName)
                    .data(sseResponse, MediaType.APPLICATION_JSON));
        } catch (NullPointerException e) {
            add(iuser);
            return send(iuser, sseEmitterName, pushInfo, addedCode);
        } catch (IOException e) {
            log.info("[SseEmitterHolder] Exception", e);
            emitterMap.remove(iuser);
            return false;
        }
        return true;
    }
}
