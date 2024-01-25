package com.team5.projrental.common.sse.holder;

import com.team5.projrental.common.sse.SseEmitterRepository;
import com.team5.projrental.common.sse.model.CatchEventProperties;
import com.team5.projrental.common.sse.model.RejectMessageInfo;
import com.team5.projrental.common.sse.model.SseResponse;
import com.team5.projrental.common.sse.responseproperties.Properties;
import com.team5.projrental.common.threadpool.MyThreadPoolHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;

@Component
@Slf4j
public class SseEmitterHolder {
    private Map<Integer, SseEmitter> emitterMap;

    private SseEmitterRepository emitterRepository;

    private ExecutorService threadPool;

    public SseEmitterHolder(SseEmitterRepository emitterRepository, MyThreadPoolHolder myThreadPoolHolder) {
        this.emitterRepository = emitterRepository;
        this.emitterMap = new ConcurrentHashMap<>();
        this.threadPool = myThreadPoolHolder.getThreadPool();
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

        //
        threadPool.execute(() -> sendFromDbMessage(iuser));

        return sseEmitter;
    }

    // + 만약 해당 유저의 SseEmitter 가 존재하지 않으면 DB 에 저장해두고, 로그인시 해당 데이터 일괄 보내기
    // 여기서 필요한건 DB에서 해당 유저에게 보내야할 푸시가 존재한다면 해당 메시지 다 담아서 푸시하기.
    public void sendFromDbMessage(Integer iuser) {
        List<RejectMessageInfo> messages = emitterRepository.findRejectedMessage(iuser);
        messages.forEach(m -> send(m.getIuser(), null, m.getMessage(), m.getCode(), m.getPkNum()));
        int deletedPushMessageFromDb = emitterRepository.deleteRejectedMessage(iuser);
        log.debug("[SseEmitterHolder.sendFromDbMessage] deletedPushMessageFromDb = {}", deletedPushMessageFromDb);
    }


    public boolean send(Integer iuser, Properties pushInfo) {
        return send(iuser, "sse push", pushInfo);
    }

    public boolean send(Integer iuser, String sseEmitterName, Properties pushInfo) {
        return send(iuser, sseEmitterName, pushInfo, null);
    }

    public boolean send(Integer iuser, String sseEmitterName, Properties pushInfo, Integer pkNum) {
        return send(iuser, sseEmitterName, pushInfo.getMessage().get(), pushInfo.getCode().get(), pkNum);
    }

    /*
    ex)
    iuser: 1
    code: 1001 @1
    message: 반납일이 지났지만 리뷰가 등록되지 않은 결제 개수 @Count
     */
    public boolean send(Integer iuser, String sseEmitterName, String message, Integer code, Integer pkNum) {
        if (sseEmitterName == null) sseEmitterName = "sse push";
        SseResponse sseResponse = SseResponse.builder()
                .iuser(iuser)
                .code(code + (pkNum != null ? " @" + pkNum : ""))
                .message(message)
                .build();

        try {
            emitterMap.get(iuser).send(SseEmitter.event()
                    .name(sseEmitterName)
                    .data(sseResponse, MediaType.APPLICATION_JSON));
        } catch (NullPointerException e) {
            int savedPushCount = emitterRepository.savePushInfoWhenNotExistsEmitterInMap(new RejectMessageInfo(iuser, message, code, pkNum));
            log.debug("[SseEmitterHolder.send] savedPushCount = {}", savedPushCount);
            return false;
        } catch (IOException e) {
            log.info("[SseEmitterHolder.send] Exception", e);
            emitterMap.remove(iuser);
            return false;
        }
        return true;
    }


}
