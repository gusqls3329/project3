package com.team5.projrental.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team5.projrental.common.exception.base.BadInformationException;
import com.team5.projrental.common.model.ErrorResultVo;
import com.team5.projrental.common.model.ResVo;
import com.team5.projrental.product.model.ProductUpdDto;
import com.team5.projrental.user.model.SigninDto;
import com.team5.projrental.user.model.SigninVo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class ProductControllerTest {

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

        String contentAsString = mockMvc.perform(MockMvcRequestBuilders.post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(dto)))
                .andReturn().getResponse().getContentAsString();
        SigninVo signinVo = om.readValue(contentAsString, SigninVo.class);
        this.token = "Bearer " + signinVo.getAccessToken();

    }

    @Test
    void getProductList() {


    }

    @Test
    void getProduct() {
    }

    @Test
    void postProduct() {
    }

    @Test
    void putProduct() throws Exception {


    }

    //
    @Test
    void delProduct() throws Exception {

        /*
        {
          "result": 1
        }
         */

        String result = mockMvc.perform(MockMvcRequestBuilders.delete("/api/prod/11?div=1")
                .header("Authorization", token)
        ).andReturn().getResponse().getContentAsString();

        ResVo resVo = om.readValue(result, ResVo.class);
        assertThat(resVo.getResult()).isEqualTo(1);

        String result2 = mockMvc.perform(MockMvcRequestBuilders.delete("/api/prod/10?div=1")
                .header("Authorization", token)
        ).andReturn().getResponse().getContentAsString();

        ErrorResultVo errorResultVo = om.readValue(result2, ErrorResultVo.class);
        org.junit.jupiter.api.Assertions.assertEquals(errorResultVo.getErrorCode(), 468);


    }

    @Test
    void getUserProductList() {
    }

    @Test
    void getAllReviews() {
    }
}