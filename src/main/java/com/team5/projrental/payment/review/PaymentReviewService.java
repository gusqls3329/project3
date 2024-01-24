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

import static com.team5.projrental.common.exception.ErrorCode.ILLEGAL_EX_MESSAGE;
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
            //한거래에 한리부만 적을 수 있도록
            int selReview = reviewMapper.selReview(loginUserPk, dto.getIpayment());
            if (selReview == 0) {
                CheckIsBuyer buyCheck = reviewMapper.selBuyRew(loginUserPk, dto.getIpayment());
                CommonUtils.ifAnyNullThrow(BadInformationException.class, ErrorCode.BAD_INFO_EX_MESSAGE,
                        buyCheck);
                if (buyCheck.getIsBuyer() == 0) {
                    dto.setContents(null);
                    dto.setRating(null);
                }
                if (buyCheck.getIsExists() != 0) {
                    int result = reviewMapper.insReview(dto);
                    if (result != 1) {
                        throw new BadInformationException(ILLEGAL_EX_MESSAGE);
                    } else {
                        //if (reviewMapper.selReview(null, dto.getIpayment()) == 2) {
                            int result2 = reviewMapper.upProductIstatus(dto.getIpayment());
                            log.info("dto.getIreview:{}",dto.getIreview());
                            if (result2 == 1) {
                                if(dto.getContents() != null && dto.getRating() != null){
                                    int chIuser = reviewMapper.selUser(dto.getIreview());
                                    SelRatVo rating = reviewMapper.selRat(chIuser);
                                    for (Integer total : rating.getTotalRat()){
                                        if (total == null){
                                             int a = rating.getCountIuser() - 1;
                                            dto.setCheckIuser(a);
                                        }
                                    }
                                    double average = (dto.getCheckIuser()-1) * rating.getAverageRat();
                                    double averageRat = (average+dto.getRating())/(dto.getCheckIuser());
                                    UpRating uprating = new UpRating();
                                    uprating.setIuser(chIuser);
                                    uprating.setRating(averageRat);
                                    int upRating = reviewMapper.upRating(uprating);
                                    if(upRating == 1){
                                        return Const.SUCCESS;
                                    }
                                }
                            }
                            throw new BadInformationException(ILLEGAL_EX_MESSAGE);
                        }
                    }
                //}
             //   throw new BadInformationException(ILLEGAL_EX_MESSAGE);
            }
            throw new BadInformationException(REVIEW_ALREADY_EXISTS_EX_MESSAGE);
        }
        throw new BadInformationException(ILLEGAL_EX_MESSAGE);
    }

    public int patchReview(UpRieDto dto) {
        int loginUserPk = authenticationFacade.getLoginUserPk();
        dto.setIuser(loginUserPk);
        //수정전 리뷰를 작성한 사람이 iuser가 맞는지 확인
        RiviewVo check = reviewMapper.selPatchRev(dto.getIreview());
        if (check.getIbuyer() == loginUserPk) {
            int result = reviewMapper.upReview(dto);
            if (result != 1) {
                throw new BadInformationException(ILLEGAL_EX_MESSAGE);
            }
            return Const.SUCCESS;
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
                    if (result != 1) {
                        throw new BadInformationException(ILLEGAL_EX_MESSAGE);
                    }
                    return Const.SUCCESS;
                }
                throw new BadInformationException(NO_SUCH_REVIEW_EX_MESSAGE);
            }
            throw new BadInformationException(ILLEGAL_EX_MESSAGE);
    }

    public SelRatVo SelRatVo(int iuser){
        return reviewMapper.selRat(iuser);
    }
}
