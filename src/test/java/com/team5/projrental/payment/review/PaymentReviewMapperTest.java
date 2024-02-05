package com.team5.projrental.payment.review;

import com.team5.projrental.payment.review.model.DelRivewDto;
import com.team5.projrental.payment.review.model.RivewDto;
import com.team5.projrental.payment.review.model.RiviewVo;
import com.team5.projrental.payment.review.model.UpRieDto;
import com.team5.projrental.user.model.CheckIsBuyer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PaymentReviewMapperTest {
    @Autowired
    private PaymentReviewMapper mapper;

    @Test
    void insReview() {
        RivewDto dto = new RivewDto();
        dto.setIuser(4);
        dto.setContents("리뷰테스트");
        dto.setIpayment(13);
        dto.setRating(5);

        int result = mapper.insReview(dto);

        assertEquals(result, 1);
    }

    @Test
    void upProductIstatus() {
        int result = mapper.upProductIstatus(6);
        assertEquals(result,1);

        int istatus = mapper.selReIstatus(6);
        assertEquals(1, istatus);
    }

    @Test
    void upReview() {
        UpRieDto dto = new UpRieDto();
        dto.setContents("변경함");
        dto.setRating(4);
        dto.setIreview(2);
        dto.setIuser(1);

        int result = mapper.upReview(dto);

        assertEquals(result, 1);

    }

    @Test
    void delReview() {
        DelRivewDto dto = new DelRivewDto();
        dto.setIuser(1);
        dto.setIreview(2);
        dto.setIstatus(-4);

        int result = mapper.delReview(dto);
        assertEquals(result,1);
    }

    @Test
    void selReIstatus() {
        int result = mapper.selReIstatus(6);
        assertEquals(-4,result);

        int result2 = mapper.selReIstatus(7);
        assertEquals(-4,result2);
    }

    @Test
    void selReview() {
        int result = mapper.selReview(1,6);
        assertEquals(1,result);
        int result2 = mapper.selReview(1,7);
        assertEquals(0,result2);
    }

    @Test
    void selBuyRew() {
        CheckIsBuyer vo = mapper.selBuyRew(1,6);
        assertEquals(vo.getIsBuyer(),1);
        assertEquals(vo.getIsExists(),1);

        CheckIsBuyer vo1 = mapper.selBuyRew(1,10);
        assertEquals(vo1.getIsBuyer(),0);
        assertEquals(vo1.getIsExists(),1);
    }

    @Test
    void selPatchRev() {
        RiviewVo vo = mapper.selPatchRev(2);
        assertEquals(vo.getIuser(),1);
        assertEquals(vo.getIpayment(),6);

        RiviewVo vo1 = mapper.selPatchRev(5);
        assertEquals(vo1.getIpayment(),14);
        assertEquals(vo1.getIuser(),2);

    }
}