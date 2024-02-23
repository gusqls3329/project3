package com.team5.projrental.payment.review;

import com.team5.projrental.payment.review.model.*;
import com.team5.projrental.user.model.CheckIsBuyer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PaymentReviewMapper {
    int insReview(RivewDto dto);

    int upReview(UpRieDto dto);
    int delReview(int ireview, Long iuser);

    String selReIstatus(Integer ipayment, Long iuser);
    int selReview(Long iuser, Integer ipayment);
    CheckIsBuyer selBuyRew(Long iuser, Integer ipayment);
    RiviewVo selPatchRev(Integer ireview);

    // 평균 별점올리기
    int selUser(Integer ipayment);
    SelRatVo selRat(Integer iuser);
    int upRating(UpRating rating);
    BeforRatingDto sleDelBefor(Integer ireview);
}
