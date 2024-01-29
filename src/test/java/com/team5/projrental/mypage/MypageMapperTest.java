package com.team5.projrental.mypage;

import com.team5.projrental.common.security.AuthenticationFacade;
import com.team5.projrental.mypage.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@Slf4j
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MypageMapperTest {

    @Autowired
    private MypageMapper mapper;

    private AuthenticationFacade authenticationFacade;

    @Test

    void getPaymentList() {
        PaymentSelDto dto = new PaymentSelDto();

        dto.setLoginedIuser(1);
        dto.setPage(1);
        dto.setRole(1);
        List<PaymentSelVo> selVo = mapper.getPaymentList(dto);

        assertEquals(selVo.size(), 1);
        dto.setLoginedIuser(1);
        dto.setPage(1);
        dto.setRole(1);

        List<PaymentSelVo> selVo2 = mapper.getPaymentList(dto);
        assertEquals(selVo2.size(), 1);
    }

    @Test
    void getIbuyerReviewList() {
        MyBuyReviewListSelDto dto = new MyBuyReviewListSelDto();
        dto.setIuser(2);
        dto.setPage(1);
        dto.setRowCount(10);

        List<MyBuyReviewListSelVo> selVo = mapper.getIbuyerReviewList(dto);
        assertEquals(selVo.size(), 1);
    }

    @Test
    void getFavList() {
        MyFavListSelDto dto = new MyFavListSelDto();
        dto.setLoginedIuser(1);
        dto.setPage(1);

        List<MyFavListSelVo> selVo = mapper.getFavList(dto);
        assertEquals(2,selVo.size());
    }
}
