package com.team5.projrental.mypage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team5.projrental.MockMvcConfig;
import com.team5.projrental.mypage.model.PaymentSelDto;
import com.team5.projrental.mypage.model.PaymentSelVo;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@MockMvcConfig
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@Import(MypageController.class)
@Transactional
class MypageControllerTest {

    String token;

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
    void getReview() throws Exception{
        String page = "1";


    }

    @Test
    void getFavList() {

    }
}
