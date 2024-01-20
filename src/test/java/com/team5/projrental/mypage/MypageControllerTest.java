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
    /*@Autowired
    private ObjectMapper mapper;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MypageService service;

    @Test
    void getPaymentList() throws Exception {
        List<PaymentSelVo> list = new ArrayList<>();

        PaymentSelVo vo = new PaymentSelVo();
        vo.setIbuyer(1);
        vo.setIuser(7);
        vo.setIproduct(25);

        list.add(vo);

        given(service.paymentList(any())).willReturn(list);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        params.add("page", "1");
        params.add("loginedIuser", "1");
        mvc.perform(
                MockMvcRequestBuilders
                        .get("/api/mypage/prod")
                        .params(params))
                .andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(list)))
                .andDo(print());
        verify(service).paymentList(any());
    }*/

}
