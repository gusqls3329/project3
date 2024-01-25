package com.team5.projrental.common.sse;

import com.team5.projrental.common.sse.model.FindDiffUserDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

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



}
