package com.team5.projrental.payment.review;

import com.team5.projrental.common.Const;
import com.team5.projrental.common.exception.ErrorCode;
import com.team5.projrental.common.exception.ErrorMessage;
import com.team5.projrental.common.exception.base.BadInformationException;
import com.team5.projrental.common.security.AuthenticationFacade;
import com.team5.projrental.common.utils.CommonUtils;
import com.team5.projrental.entities.enums.PaymentInfoStatus;
import com.team5.projrental.payment.review.model.*;
import com.team5.projrental.user.model.CheckIsBuyer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.team5.projrental.common.exception.ErrorCode.*;
import static com.team5.projrental.common.exception.ErrorMessage.NO_SUCH_REVIEW_EX_MESSAGE;
import static com.team5.projrental.common.exception.ErrorMessage.REVIEW_ALREADY_EXISTS_EX_MESSAGE;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentReviewService {
    private final PaymentReviewMapper reviewMapper;
    private final AuthenticationFacade authenticationFacade;

    @Transactional
    public int postReview(RivewDto dto) {
        Long loginUserPk = authenticationFacade.getLoginUserPk();
        dto.setIuser(loginUserPk);

        // 0: 리뷰를 작성하지 않음
        // null: not null
        // contents 도 not null

        //t_payment 상태가 -4 일때만 리뷰쓸수 있도록
        String istatus = reviewMapper.selReIstatus(dto.getIpayment(), loginUserPk);
        if (istatus == PaymentInfoStatus.COMPLETED.toString()) {
            //로그인한 유저가 리뷰를 적었던건지 확인하는것
            int selReview = reviewMapper.selReview(loginUserPk, dto.getIpayment());
            if (selReview == 0) {
                CheckIsBuyer buyCheck = reviewMapper.selBuyRew(loginUserPk, dto.getIpayment());
                CommonUtils.ifAnyNullThrow(BadInformationException.class, BAD_INFO_EX_MESSAGE, buyCheck);
                if (buyCheck.getIsBuyer() == 0) {
                    throw new BadInformationException(ILLEGAL_EX_MESSAGE);
                }

                int result = reviewMapper.insReview(dto);
                if (result == 0) {
                    throw new RuntimeException();
                }

                if (buyCheck.getIsBuyer() == 1) {
                    if (dto.getRating() == null && (dto.getContents() == null || dto.getContents().equals(""))) {
                        throw new BadInformationException(ILLEGAL_EX_MESSAGE);
                    }
                    int chIuser = reviewMapper.selUser(dto.getIpayment());
                    SelRatVo vo = reviewMapper.selRat(chIuser);
                    double average = (vo.getCountIre() - 1) * vo.getRating();
                    double averageRat = Math.round(((average + dto.getRating()) / vo.getCountIre()) * 10.0) / 10.0;
                    UpRating uprating = new UpRating();
                    uprating.setIuser(chIuser);
                    uprating.setRating(averageRat);
                    int r = reviewMapper.upRating(uprating);
                    if (r != 1) {
                        throw new RuntimeException();
                    }
                }
                return dto.getIreview().intValue();

            }
            throw new BadInformationException(REVIEW_ALREADY_EXISTS_EX_MESSAGE);
        }
        throw new BadInformationException(BAD_PRODUCT_ISTATUS_EX_MESSAGE);
    }

    public int patchReview(UpRieDto dto) { //구매자면 잘못된요청
        CommonUtils.ifAllNullThrow(BadInformationException.class, BAD_INFO_EX_MESSAGE,
                dto.getContents(), dto.getRating());
        Long loginUserPk = authenticationFacade.getLoginUserPk();
        dto.setIuser(loginUserPk);
        RiviewVo check = reviewMapper.selPatchRev(dto.getIreview());
        //수정하려는 유저가 구매자가 맞는지

        if (check == null) {
            throw new BadInformationException(BAD_INFO_EX_MESSAGE);
        }

        CheckIsBuyer buyCheck = reviewMapper.selBuyRew(loginUserPk, check.getIpayment());
        if (buyCheck.getIsBuyer() == 1) {
            //수정전 리뷰를 작성한 사람이 iuser가 맞는지 확인
            if (check.getIuser() == loginUserPk) {
                reviewMapper.upReview(dto);
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

                    return Const.SUCCESS;
                }
                throw new BadInformationException(ILLEGAL_EX_MESSAGE);
            }
            throw new BadInformationException(ILLEGAL_EX_MESSAGE);
        }
        throw new BadInformationException(ILLEGAL_EX_MESSAGE);
    }

    public int delReview(DelRivewDto dto) {
        Long loginUserPk = authenticationFacade.getLoginUserPk();
        dto.setIuser(loginUserPk);
        //삭제전 리뷰를 작성한 사람이 iuser가 맞는지 확인
        RiviewVo check = reviewMapper.selPatchRev(dto.getIreview());
        if (check == null) {
            throw new BadInformationException(BAD_INFO_EX_MESSAGE);
        }
        if (check.getIuser() == loginUserPk) {
            //리뷰를 등록한 사람이 판매자일 경우 삭제불가능 처리
            CheckIsBuyer buyCheck = reviewMapper.selBuyRew(loginUserPk, check.getIpayment());
            CommonUtils.ifAnyNullThrow(BadInformationException.class, BAD_INFO_EX_MESSAGE, buyCheck);
            if (buyCheck.getIsBuyer() == 0) {
                throw new BadInformationException(ILLEGAL_EX_MESSAGE);
            }

            // 리뷰를 삭제하기전 t_payment의 istatus를 확인해 삭제가능한 상태가 맞는지 확인
            String istatus = reviewMapper.selReIstatus(check.getIpayment(), loginUserPk);
            if (istatus == PaymentInfoStatus.COMPLETED.toString()) {
                dto.setIstatus(istatus);
                BeforRatingDto beforRatingDto = reviewMapper.sleDelBefor(dto.getIreview());
                if (beforRatingDto.getRating() != null && beforRatingDto.getRating() != 0) {
                    int result = reviewMapper.delReview(dto.getIreview(), dto.getIuser());
                    if (result != 1) {
                        throw new BadInformationException(ILLEGAL_EX_MESSAGE);
                    }
                    int chIuser = reviewMapper.selUser(check.getIpayment());
                    SelRatVo vo = reviewMapper.selRat(chIuser);
                    double average = vo.getCountIre() * vo.getRating();
                    double v = (average - beforRatingDto.getRating()) / vo.getCountIre();
                    double averageRat = Math.round(v * 10) / 10.0;
                    UpRating uprating = new UpRating();
                    uprating.setIuser(chIuser);
                    uprating.setRating(averageRat);
                    int upRating = reviewMapper.upRating(uprating);

                }

                return Const.SUCCESS;
            }
            throw new BadInformationException(NO_SUCH_REVIEW_EX_MESSAGE);
        }
        throw new BadInformationException(ILLEGAL_EX_MESSAGE);
    }

}
