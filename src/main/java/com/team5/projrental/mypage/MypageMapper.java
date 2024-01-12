package com.team5.projrental.mypage;

import com.team5.projrental.mypage.model.PaymentSelDto;
import com.team5.projrental.mypage.model.PaymentSelVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MypageMapper {

    List<PaymentSelVo> getPaymentList(PaymentSelDto dto);
}
