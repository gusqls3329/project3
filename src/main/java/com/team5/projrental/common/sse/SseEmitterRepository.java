package com.team5.projrental.common.sse;

import com.team5.projrental.common.sse.model.FindDiffUserDto;
import com.team5.projrental.common.sse.model.RejectMessageInfo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class SseEmitterRepository {

    private final SseEmitterMapper emitterMapper;

    public int findExpiredPaymentCountBy(int iuser) {
        return emitterMapper.findExpiredPaymentCountBy(iuser);
    }

    public FindDiffUserDto findDiffUserBy(int ipayment, int iuser) {
        return emitterMapper.findDiffUserBy(ipayment, iuser);
    }

    public List<RejectMessageInfo> findRejectedMessage(int iuser) {
        return emitterMapper.findRejectedMessage(iuser);
    }

    public int deleteRejectedMessage(int iuser) {
        return emitterMapper.deleteRejectedMessage(iuser);
    }

    public int savePushInfoWhenNotExistsEmitterInMap(RejectMessageInfo dto) {
        return emitterMapper.deleteRejectedMessage(dto);
    }

}
