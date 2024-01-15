package com.team5.projrental.mypage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team5.projrental.MockMvcConfig;
import com.team5.projrental.mypage.model.PaymentSelVo;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.mockito.BDDMockito.given;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@MockMvcConfig
@WebMvcTest(MypageController.class)
public class MypageControllerTest {
    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MypageService service;

    @SneakyThrows
    @Test
    void getPaymentList() throws Exception {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("page", "0");
        params.add("rowCount", "16");
        params.add("loginedIuser", "2"); // 입력
        params.add("role", "2");

        List<PaymentSelVo> selVoList = new ArrayList<>();
        PaymentSelVo vo = new PaymentSelVo();
        vo.setIbuyer(3);
        vo.setIuser(2);
        vo.setIproduct(2);
        vo.setIpayment(4);
        vo.setDeposit(700000);
        vo.setPrice(40000);
        vo.setRentalDuration(2);
        vo.setCancel(1);
        vo.setTargetIuser(2);
        vo.setTargetNick("인사이드아웃");

        selVoList.add(vo);

        given(service.paymentList(any())).willReturn(selVoList);

        String json = mapper.writeValueAsString(selVoList);

        mvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/mypage"))
                                //.params(params))
                .andDo(print())
                .andExpect(content().string(json));
        verify(service).paymentList(any());
    }




}
