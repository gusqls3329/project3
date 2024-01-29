package com.team5.projrental.mypage;

import com.team5.projrental.common.SecurityProperties;
import com.team5.projrental.common.security.AuthenticationFacade;
import com.team5.projrental.common.utils.KakaoAxisGenerator;
import com.team5.projrental.common.utils.CookieUtils;
import com.team5.projrental.common.utils.MyFileUtils;
import com.team5.projrental.mypage.model.*;
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
import static org.mockito.BDDMockito.given;



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
        assertEquals(list.get(0).getIbuyer(),1);
        assertEquals(list.get(0).getIuser(), 7);
    }

    @Test
    void selIbuyerReviewList() {
        MyBuyReviewListSelDto dto = new MyBuyReviewListSelDto();
        dto.setIuser(2);
        dto.setPage(1);

        MyBuyReviewListSelVo vo = new MyBuyReviewListSelVo();
        vo.setIreview(4);
        vo.setTitle("캐논 850d 대여할사람 구해요");
        vo.setNick("천재현민");
        vo.setRaiting(4);
        vo.setContents("3번 현일씨 잘빌렸어요");
        vo.setProdPic("prod\\14\\4cf20918-5b16-4c18-ae61-8ad8c1d27b44.jpg");
        vo.setIproduct(14);
        vo.setLoginedUserPic("user\\2\\6bbcdb41-b37b-4c91-abdc-dbcb6a18c5b8.jpg");


        MyBuyReviewListSelVo vo2 = new MyBuyReviewListSelVo();
        vo2.setIreview(5);
        vo2.setTitle("2번갤럭시S24플러스 빌려가세요");
        vo2.setNick("천재현민");
        vo2.setRaiting(4);
        vo2.setContents("1번 바보현빈씨 갤럭시s24플러스 감사히 잘썼습니다.");
        vo2.setProdPic("prod\\11\\297ff48c-4c7f-4efa-a1a8-8c6c67909c46.jpg");
        vo2.setIproduct(11);
        vo2.setLoginedUserPic("user\\2\\6bbcdb41-b37b-4c91-abdc-dbcb6a18c5b8.jpg");

        List<MyBuyReviewListSelVo> list = new ArrayList<>();
        list.add(vo);
        list.add(vo2);

        given(mapper.getIbuyerReviewList(dto)).willReturn(list);
        given(mapper.getIbuyerReviewList(any())).willReturn(list);


        assertEquals(4, list.get(0).getIreview());
        assertEquals(14, list.get(0).getIproduct());
        assertEquals("캐논 850d 대여할사람 구해요", list.get(0).getTitle());

        assertEquals(5, list.get(1).getIreview());
        assertEquals(11,list.get(1).getIproduct());
        assertEquals("2번갤럭시S24플러스 빌려가세요", list.get(1).getTitle());

    }

    @Test
    void selMyFavList() {
        MyFavListSelDto dto = new MyFavListSelDto();
        dto.setPage(1);
        dto.setLoginedIuser(1);

        MyFavListSelVo vo = new MyFavListSelVo();
        vo.setIproduct(12);
        vo.setTitle("그램노트 빌리실분");

        MyFavListSelVo vo2 = new MyFavListSelVo();
        vo2.setIproduct(14);
        vo2.setTitle("캐논 850d 대여할사람 구해요");

        MyFavListSelVo vo3 = new MyFavListSelVo();
        vo3.setIproduct(25);
        vo3.setTitle("갤럭시워치6 47mm 로렉스 커스텀 대여합니다");

        List<MyFavListSelVo> selVo = new ArrayList<>();
        selVo.add(vo);
        selVo.add(vo2);
        selVo.add(vo3);


        given(mapper.getFavList(any())).willReturn(selVo);

        assertEquals(12, selVo.get(0).getIproduct());
        assertEquals("그램노트 빌리실분", selVo.get(0).getTitle());

        assertEquals(14, selVo.get(1).getIproduct());
        assertEquals("캐논 850d 대여할사람 구해요", selVo.get(1).getTitle());

        assertEquals(25, selVo.get(2).getIproduct());
        assertEquals("갤럭시워치6 47mm 로렉스 커스텀 대여합니다", selVo.get(2).getTitle());
    }
}