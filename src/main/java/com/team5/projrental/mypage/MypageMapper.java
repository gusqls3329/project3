package com.team5.projrental.mypage;

import com.team5.projrental.mypage.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MypageMapper {

    List<PaymentSelVo> getPaymentList(PaymentSelDto dto);
    List<MyBuyReviewListSelVo> getIbuyerReviewList(MyBuyReviewListSelDto dto);
    List<MyFavListSelVo> getFavList(MyFavListSelDto dto);
}
