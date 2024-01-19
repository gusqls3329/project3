package com.team5.projrental.payment.review;

import com.team5.projrental.common.Const;
import com.team5.projrental.common.exception.base.BadInformationException;
import com.team5.projrental.common.security.AuthenticationFacade;
import com.team5.projrental.payment.review.model.DelRivewDto;
import com.team5.projrental.payment.review.model.RivewDto;
import com.team5.projrental.payment.review.model.RiviewVo;
import com.team5.projrental.payment.review.model.UpRieDto;
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
        int selReview = reviewMapper.selReview(loginUserPk, dto.getIpayment());
        if (selReview == 0) {
            int buyCheck = reviewMapper.selBuyRew(loginUserPk, dto.getIpayment());
            if (buyCheck == dto.getIpayment()) {
                int result = reviewMapper.insReview(dto);
                if (result != 1) {
                    throw new BadInformationException(ILLEGAL_EX_MESSAGE);
                } else {
                    int result2 = reviewMapper.upProductIstatus(dto.getIpayment());
                    if (result2 != 1) {
                        throw new BadInformationException(ILLEGAL_EX_MESSAGE);
                    }
                    return Const.SUCCESS;
                }
            }
            throw new BadInformationException(ILLEGAL_EX_MESSAGE);
        }
        throw new BadInformationException(REVIEW_ALREADY_EXISTS_EX_MESSAGE);
    }

    public int patchReview(UpRieDto dto) {
        int loginUserPk = authenticationFacade.getLoginUserPk();
        dto.setIuser(loginUserPk);
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
        RiviewVo check = reviewMapper.selPatchRev(dto.getIreview());
        if (check.getIbuyer() == loginUserPk) {
            Integer selReview = reviewMapper.selReview(loginUserPk, check.getIpayment());
            if (selReview == 1) {
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
}
