package com.team5.projrental.mypage;

import com.team5.projrental.mypage.model.PaymentSelDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MypageMapperTest {

    @Autowired
    private MypageMapper mapper;

    @Test
    void getPaymentList() {
        PaymentSelDto dto = new PaymentSelDto();
        dto.setPage(1);
        dto.setRole(1);
    }

    @Test
    void getIbuyerReviewList() {
    }

    @Test
    void getFavList() {
    }
}