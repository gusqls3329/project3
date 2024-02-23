package com.team5.projrental.common.sse;

import com.team5.projrental.common.sse.model.FindDiffUserDto;
import com.team5.projrental.common.sse.model.SseMessageInfo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class SseEmitterRepository {

    private final SseEmitterMapper emitterMapper;

    public int findExpiredPaymentCountBy(Long iuser) {
        return emitterMapper.findExpiredPaymentCountBy(iuser);
    }

    public FindDiffUserDto findDiffUserBy(int ipayment, Long iuser) {
        return emitterMapper.findDiffUserBy(ipayment, iuser);
    }

    public List<SseMessageInfo> findRejectedMessage(Long iuser) {
        return emitterMapper.findRejectedMessage(iuser);
    }

    public int deleteRejectedMessage(Long iuser) {
        return emitterMapper.deleteRejectedMessage(iuser);
    }

    public int savePushInfoWhenNotExistsEmitterInMap(SseMessageInfo dto) {
        return emitterMapper.savePushInfoWhenNotExistsEmitterInMap(dto);
    }

}
