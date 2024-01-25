package com.team5.projrental.common.sse;

import com.team5.projrental.common.sse.model.FindDiffUserDto;
import com.team5.projrental.common.sse.model.RejectMessageInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SseEmitterMapper {
    int findExpiredPaymentCountBy(int iuser);

    FindDiffUserDto findDiffUserBy(int ipayment, int iuser);

    List<RejectMessageInfo> findRejectedMessage(int iuser);

    int deleteRejectedMessage(int iuser);

    int deleteRejectedMessage(RejectMessageInfo dto);
}
