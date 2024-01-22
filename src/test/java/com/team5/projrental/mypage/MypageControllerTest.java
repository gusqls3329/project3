package com.team5.projrental.mypage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team5.projrental.product.ProductController;
import com.team5.projrental.user.model.SigninDto;
import com.team5.projrental.user.model.SigninVo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@Transactional
class MypageControllerTest {

    String token;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper om;

    @Autowired
    ProductController controller;

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
    void getPaymentList() {

    }

    @Test
    void getReview() {

    }

    @Test
    void getFavList() {

    }
}