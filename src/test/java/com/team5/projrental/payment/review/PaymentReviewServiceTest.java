package com.team5.projrental.payment.review;

import com.team5.projrental.common.security.AuthenticationFacade;
import com.team5.projrental.payment.review.model.DelRivewDto;
import com.team5.projrental.payment.review.model.RivewDto;
import com.team5.projrental.payment.review.model.RiviewVo;
import com.team5.projrental.payment.review.model.UpRieDto;
import com.team5.projrental.user.model.CheckIsBuyer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(SpringExtension.class)
@Import({PaymentReviewService.class})
class PaymentReviewServiceTest {
    @MockBean
    private PaymentReviewMapper reviewMapper;
    @MockBean
    private AuthenticationFacade authenticationFacade;

    @Autowired
    private PaymentReviewService service;

    @Test
    void postReview() {
        when(authenticationFacade.getLoginUserPk()).thenReturn(4);
        when(reviewMapper.selReIstatus(any())).thenReturn(-4);
        when(reviewMapper.selReview(4,13)).thenReturn(0);
        CheckIsBuyer vo = new CheckIsBuyer();
        vo.setIsBuyer(1);
        vo.setIsExists(1);
        when(reviewMapper.selBuyRew(any(),any())).thenReturn(vo);
        when(reviewMapper.insReview(any())).thenReturn(1);
        when(reviewMapper.upProductIstatus(any())).thenReturn(1);

        RivewDto dto = new RivewDto();
        dto.setIuser(4);
        dto.setContents("리뷰테스트");
        dto.setIpayment(13);
        dto.setRating(5);

        int result = service.postReview(dto);

        verify(authenticationFacade).getLoginUserPk();
        verify(reviewMapper).selReIstatus(any());
        verify(reviewMapper).selReview(4,13);
        verify(reviewMapper).selBuyRew(any(),any());
        verify(reviewMapper).insReview(any());

        assertEquals(1,result);
    }

    @Test
    void patchReview() {
        when(authenticationFacade.getLoginUserPk()).thenReturn(1);
        RiviewVo vo = new RiviewVo();
        vo.setIbuyer(1);
        when(reviewMapper.selPatchRev(any())).thenReturn(vo);
        when(reviewMapper.upReview(any())).thenReturn(1);

        UpRieDto dto = new UpRieDto();
        dto.setContents("변경함");
        dto.setRating(4);
        dto.setIreview(2);
        dto.setIuser(1);

        int result = service.patchReview(dto);

        assertEquals(1,result);

        verify(authenticationFacade).getLoginUserPk();
        verify(reviewMapper).selPatchRev(2);
        verify(reviewMapper).upReview(dto);
    }

    @Test
    void delReview() {
        when(authenticationFacade.getLoginUserPk()).thenReturn(1);
        RiviewVo vo = new RiviewVo();
        vo.setIbuyer(1);
        when(reviewMapper.selPatchRev(any())).thenReturn(vo);
        when(reviewMapper.selReIstatus(any())).thenReturn(-2);
        when(reviewMapper.selReview(any(),any())).thenReturn(1);
        when(reviewMapper.delReview(any())).thenReturn(1);

        DelRivewDto dto = new DelRivewDto();
        dto.setIuser(1);
        dto.setIreview(2);
        dto.setIstatus(1);

        int result = service.delReview(dto);
        assertEquals(result,1);

        verify(authenticationFacade).getLoginUserPk();
        verify(reviewMapper).selPatchRev(2);
        verify(reviewMapper).selReIstatus(any());
        verify(reviewMapper).selReview(any(),any());
        verify(reviewMapper).delReview(dto);
    }
}