package com.team5.projrental.payment.review;

import com.team5.projrental.payment.review.model.*;
import com.team5.projrental.user.model.CheckIsBuyer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PaymentReviewMapper {
    int insReview(RivewDto dto);
    int selUpProIs(Integer ipayment);

    int upProductIstatus(Integer ipayment);
    int upReview(UpRieDto dto);
    int delReview(DelRivewDto dto);

    int selReIstatus(Integer ipayment);
    int selReview(Integer iuser, Integer ipayment);
    CheckIsBuyer selBuyRew(Integer iuser, Integer ipayment);
    RiviewVo selPatchRev(Integer ireview);

    // 평균 별점올리기
    int selUser(Integer ipayment);
    SelRatVo selRat(Integer iuser);
    int upRating(UpRating rating);
}
