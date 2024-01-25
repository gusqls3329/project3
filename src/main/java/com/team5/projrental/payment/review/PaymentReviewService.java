package com.team5.projrental.payment.review;

import com.team5.projrental.common.Const;
import com.team5.projrental.common.exception.ErrorCode;
import com.team5.projrental.common.exception.ErrorMessage;
import com.team5.projrental.common.exception.base.BadInformationException;
import com.team5.projrental.common.security.AuthenticationFacade;
import com.team5.projrental.common.utils.CommonUtils;
import com.team5.projrental.payment.review.model.*;
import com.team5.projrental.user.model.CheckIsBuyer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.team5.projrental.common.exception.ErrorCode.*;
import static com.team5.projrental.common.exception.ErrorMessage.NO_SUCH_REVIEW_EX_MESSAGE;
import static com.team5.projrental.common.exception.ErrorMessage.REVIEW_ALREADY_EXISTS_EX_MESSAGE;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentReviewService {
    private final PaymentReviewMapper reviewMapper;
    private final AuthenticationFacade authenticationFacade;

    public int postReview(RivewDto dto) {
        int loginUserPk = authenticationFacade.getLoginUserPk();
        dto.setIuser(loginUserPk);

        //t_payment 상태가 -4 일때만 리뷰쓸수 있도록
        Integer istatus = reviewMapper.selReIstatus(dto.getIpayment());
        if (istatus == -4) {
            //로그인한 유저가 리뷰를 적었던건지 확인하는것
            int selReview = reviewMapper.selReview(loginUserPk, dto.getIpayment());
            if (selReview == 0) {
                CheckIsBuyer buyCheck = reviewMapper.selBuyRew(loginUserPk, dto.getIpayment());
                CommonUtils.ifAnyNullThrow(BadInformationException.class, BAD_INFO_EX_MESSAGE, buyCheck);
                if (buyCheck.getIsBuyer() == 0) {
                    dto.setContents(null);
                    dto.setRating(null);
                }
                int result = reviewMapper.insReview(dto);
                if (result == 0) {
                    throw new BadInformationException(ErrorMessage.ILLEGAL_EX_MESSAGE);
                }

                if (buyCheck.getIsBuyer() == 1 && dto.getRating() != null) {
                    int chIuser = reviewMapper.selUser(dto.getIpayment());
                    SelRatVo vo = reviewMapper.selRat(chIuser);
                    double average = (vo.getCountIre() - 1) * vo.getRating();
                    double v = (average + dto.getRating()) / vo.getCountIre();
                    double averageRat = Math.round(v * 10 ) / 10.0;
                    UpRating uprating = new UpRating();
                    uprating.setIuser(chIuser);
                    uprating.setRating(averageRat);
                    int upRating = reviewMapper.upRating(uprating);
                    if (upRating != 1) {
                        throw new BadInformationException(REVIEW_ALREADY_EXISTS_EX_MESSAGE);
                    }
                }
                int countIstatus = reviewMapper.selUpProIs(dto.getIpayment());

                if (countIstatus == 2) {
                    int result2 = reviewMapper.upProductIstatus(dto.getIpayment());
                    if (result2 == 0) {
                        throw new BadInformationException(ErrorCode.ILLEGAL_EX_MESSAGE);
                    }
                    return Const.SUCCESS;
                } else {
                    return Const.SUCCESS;
                }

            }
            throw new BadInformationException(REVIEW_ALREADY_EXISTS_EX_MESSAGE);
        }
        throw new BadInformationException(BAD_PRODUCT_ISTATUS_EX_MESSAGE);
    }

    public int patchReview(UpRieDto dto) { //구매자면 잘못된요청
        int loginUserPk = authenticationFacade.getLoginUserPk();
        dto.setIuser(loginUserPk);
        RiviewVo check = reviewMapper.selPatchRev(dto.getIreview());
        //수정하려는 유저가 구매자가 맞는지 판마자면 수정할 수 없음
        CheckIsBuyer buyCheck = reviewMapper.selBuyRew(loginUserPk, check.getIpayment());
        if (buyCheck.getIsBuyer() == 1) {
            //수정전 리뷰를 작성한 사람이 iuser가 맞는지 확인

            if (check.getIuser() == loginUserPk) {
                int result = reviewMapper.upReview(dto);
                if (result != 1) {
                    throw new BadInformationException(ILLEGAL_EX_MESSAGE);
                }
                if (buyCheck.getIsBuyer() == 1 && dto.getRating() != null) {
                    int chIuser = reviewMapper.selUser(check.getIpayment());
                    SelRatVo vo = reviewMapper.selRat(chIuser);
                    double average = vo.getCountIre() * vo.getRating();
                    double v = (average + dto.getRating()) / vo.getCountIre();
                    double averageRat = Math.round(v * 10) / 10.0;
                    UpRating uprating = new UpRating();
                    uprating.setIuser(chIuser);
                    uprating.setRating(averageRat);
                    int upRating = reviewMapper.upRating(uprating);
                    if (upRating != 1) {
                        throw new BadInformationException(REVIEW_ALREADY_EXISTS_EX_MESSAGE);
                    }
                    return Const.SUCCESS;
                }
                throw new BadInformationException(ILLEGAL_EX_MESSAGE);
            }
            throw new BadInformationException(ILLEGAL_EX_MESSAGE);
        }
        throw new BadInformationException(ILLEGAL_EX_MESSAGE);
    }

    public int delReview(DelRivewDto dto) {
        int loginUserPk = authenticationFacade.getLoginUserPk();
        dto.setIuser(loginUserPk);
        //삭제전 리뷰를 작성한 사람이 iuser가 맞는지 확인
        RiviewVo check = reviewMapper.selPatchRev(dto.getIreview());
            if (check.getIbuyer() == loginUserPk) {
                // 리뷰를 삭제하기전 t_payment의 istatus를 확인해 삭제가능한 상태가 맞는지 확인
                Integer istatus = reviewMapper.selReIstatus(check.getIpayment());
                if (istatus == 1 || istatus == -2 || istatus == -3) {
                    dto.setIstatus(istatus);
                    int result = reviewMapper.delReview(dto);
                    BeforRatingDto beforRatingDto = reviewMapper.sleDelBefor(dto.getIreview());
                    if (result != 1) {
                        throw new BadInformationException(ILLEGAL_EX_MESSAGE);
                    }
                    if(beforRatingDto.getRating() != null && beforRatingDto.getRating() != 0){
                        int chIuser = reviewMapper.selUser(check.getIpayment());
                        SelRatVo vo = reviewMapper.selRat(chIuser);
                        double average = vo.getCountIre() * vo.getRating();
                        double v = (average - beforRatingDto.getRating()) / vo.getCountIre();
                        double averageRat = Math.round(v * 10) / 10.0;
                        UpRating uprating = new UpRating();
                        uprating.setIuser(chIuser);
                        uprating.setRating(averageRat);
                        int upRating = reviewMapper.upRating(uprating);
                        if (upRating != 1) {
                            throw new BadInformationException(REVIEW_ALREADY_EXISTS_EX_MESSAGE);
                        }
                    }

                    return Const.SUCCESS;
                }
                throw new BadInformationException(NO_SUCH_REVIEW_EX_MESSAGE);
            } throw new BadInformationException(ILLEGAL_EX_MESSAGE);
        }

}
