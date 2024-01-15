package com.team5.projrental.mypage;

import com.team5.projrental.mypage.model.*;
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

    public List<MyBuyReviewListSelVo> selIbuyerReviewList(MyBuyReviewListSelDto dto){
        return mapper.getIbuyerReviewList(dto);
    }

    public List<MyFavListSelVo> selMyFavList(MyFavListSelDto dto) {
        return mapper.getFavList(dto);
    }


}
