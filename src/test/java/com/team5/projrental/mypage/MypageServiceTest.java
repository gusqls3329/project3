package com.team5.projrental.mypage;

import com.team5.projrental.common.SecurityProperties;
import com.team5.projrental.common.security.AuthenticationFacade;
import com.team5.projrental.common.utils.KakaoAxisGenerator;
import com.team5.projrental.common.utils.CookieUtils;
import com.team5.projrental.common.utils.MyFileUtils;
import com.team5.projrental.mypage.model.PaymentSelDto;
import com.team5.projrental.mypage.model.PaymentSelVo;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@ExtendWith(SpringExtension.class)
@Import({MypageService.class})
class MypageServiceTest {

    @MockBean
    private MypageMapper mapper;
    @MockBean
    private PasswordEncoder passwordEncoder;
    @MockBean
    private SecurityProperties securityProperties;
    @MockBean
    private CookieUtils cookieUtils;
    @MockBean
    private AuthenticationFacade authenticationFacade;
    @MockBean
    private KakaoAxisGenerator axisGenerator;
    @MockBean
    private MyFileUtils myFileUtils;
    @MockBean
    private HttpServletResponse res;

    @Autowired
    private MypageService service;

    @Test
    void paymentList() {
        PaymentSelDto dto = new PaymentSelDto();
        dto.setPage(1);
        dto.setRole(1);
        dto.setLoginedIuser(1);

        when(mapper.getPaymentList(dto)).thenReturn(null);
        PaymentSelVo vo = new PaymentSelVo();
        vo.setIbuyer(1);
        vo.setIuser(7);
        vo.setIproduct(25);
        List<PaymentSelVo> list = new ArrayList<>();
        list.add(vo);
        when(mapper.getPaymentList(dto)).thenReturn(list);

        list = service.paymentList(dto);
        verify(mapper).getPaymentList(any());
        log.info("ibuyer:{}", vo.getIbuyer());
        assertEquals(vo.getIbuyer(),1);
        assertEquals(vo.getIuser(), 7);
    }

    @Test
    void selIbuyerReviewList() {
    }

    @Test
    void selMyFavList() {
    }
}