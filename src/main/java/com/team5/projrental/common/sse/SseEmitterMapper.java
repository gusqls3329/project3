package com.team5.projrental.common.sse;

import com.team5.projrental.common.sse.model.FindDiffUserDto;
import com.team5.projrental.common.sse.model.RejectMessageInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SseEmitterMapper {
    int findExpiredPaymentCountBy(Long iuser);

    FindDiffUserDto findDiffUserBy(int ipayment, Long iuser);

    List<RejectMessageInfo> findRejectedMessage(Long iuser);

    int deleteRejectedMessage(Long iuser);

    int savePushInfoWhenNotExistsEmitterInMap(RejectMessageInfo dto);
}
