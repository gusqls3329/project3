package com.team5.projrental.mypage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team5.projrental.MockMvcConfig;
import com.team5.projrental.chat.model.ChatMsgDelDto;
import com.team5.projrental.common.Const;
import com.team5.projrental.common.model.ResVo;
import com.team5.projrental.mypage.model.*;
import com.team5.projrental.payment.review.PaymentReviewController;
import com.team5.projrental.product.ProductController;
import com.team5.projrental.user.model.SigninDto;
import com.team5.projrental.user.model.SigninVo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@MockMvcConfig
@Import(MypageController.class)
class MypageControllerTest {

    String token;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private MypageService service;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper om;

    @Autowired
    MypageController controller;

    @BeforeEach
    public void before() throws Exception {
        SigninDto dto = new SigninDto();
        dto.setUid("qwqwqw11");
        dto.setUpw("12121212");


        String contestAsString = mockMvc.perform(MockMvcRequestBuilders.post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(dto)))
                .andReturn().getResponse().getContentAsString();
        SigninVo signinVo = om.readValue(contestAsString, SigninVo.class);
        this.token = "Bearer " + signinVo.getAccessToken();
    }


    @Test
    void getPaymentList() throws Exception {

        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("page", "1");
        requestParams.add("role", "1");

        PaymentSelDto dto = new PaymentSelDto();
        dto.setPage(1);
        dto.setRole(1);

        PaymentSelVo vo = new PaymentSelVo();
        vo.setIproduct(25);
        vo.setIbuyer(1);

        //List<PaymentSelVo> selVoList = controller.getPaymentList(1, 1);
        List<PaymentSelVo> selVoList = new ArrayList<>();
        selVoList.add(vo);

        given(service.paymentList(any(PaymentSelDto.class))).willReturn(selVoList);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/mypage/prod")
                        .params(requestParams))
                .andExpect(status().isOk())
                .andDo(print());
    }


    @Test
    void getReview() throws Exception {
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("page", "1");

        MyBuyReviewListSelDto dto = new MyBuyReviewListSelDto();
        dto.setIuser(1);
        dto.setPage(1);

        MyBuyReviewListSelVo selVo = new MyBuyReviewListSelVo();
        selVo.setRaiting(5);
        selVo.setContents("잘썻습니다");

        List<MyBuyReviewListSelVo> list = new ArrayList<>();
        list.add(selVo);

        given(service.selIbuyerReviewList(any())).willReturn(list);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/mypage/review")
                .params(requestParams))
                .andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(list)))
                .andDo(print());

        verify(service).selIbuyerReviewList(any());
    }

    @Test
    void getFavList() throws Exception {
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("page", "1");

        MyFavListSelDto dto = new MyFavListSelDto();
        dto.setLoginedIuser(1);

        MyFavListSelVo selVo = new MyFavListSelVo();
        selVo.setTitle("그램노트 빌리실분");
        selVo.setNick("천재현민");
        selVo.setIproduct(12);

        List<MyFavListSelVo> list = new ArrayList<>();
        list.add(selVo);

        given(service.selMyFavList(any())).willReturn(list);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/mypage/fav")
                        .params(requestParams))
                .andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(list)))
                .andDo(print());
        verify(service).selMyFavList(any());
    }
}
