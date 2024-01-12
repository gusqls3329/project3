package com.team5.projrental.mypage;

import com.team5.projrental.mypage.model.PaymentSelDto;
import com.team5.projrental.mypage.model.PaymentSelVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MypageService {
    private final MypageMapper mapper;

    public List<PaymentSelVo> paymentList(PaymentSelDto dto){

        return mapper.getPaymentList(dto);
    }
}
