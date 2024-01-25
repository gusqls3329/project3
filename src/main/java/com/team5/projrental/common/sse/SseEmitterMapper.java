package com.team5.projrental.common.sse;

import com.team5.projrental.common.sse.model.FindDiffUserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SseEmitterMapper {
    int findExpiredPaymentCountBy(int iuser);

    FindDiffUserDto findDiffUserBy(int ipayment, int iuser);
}
